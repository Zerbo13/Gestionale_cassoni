package mattiazerbini.gestionale_cassoni.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets =
            new ConcurrentHashMap<>();

    private Bucket creaBucket() {

        Bandwidth limite = Bandwidth.classic(5,
                Refill.intervally(5, Duration.ofMinutes(2)));

        return Bucket.builder()
                .addLimit(limite)
                .build();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (!path.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String ip = request.getRemoteAddr();

        Bucket bucket = buckets.computeIfAbsent(ip,
                key -> creaBucket());

        if (bucket.tryConsume(1)) {

            filterChain.doFilter(request, response);

        } else {

            response.setStatus(429);

            response.setContentType("application/json");

            response.getWriter().write("""
                    {"errore": "Troppe richieste. Riprova tra poco."}""");
        }
    }
}