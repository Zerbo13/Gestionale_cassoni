package mattiazerbini.gestionale_cassoni.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.services.UtenteService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTCheckedFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UtenteService utenteService;

    public JWTCheckedFilter(
            JWTTools jwtTools,
            UtenteService utenteService
    ) {
        this.jwtTools = jwtTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Se non c'è il token continua.
        // Sarà poi SecurityConfig a decidere se la rotta è accessibile.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            // Controllo che il token sia valido
            jwtTools.verifyToken(token);

            // Recupero l'id dell'utente dal token
            Long userId = jwtTools.extractIdFromToken(token);

            // Recupero l'utente dal database
            Utente utente = utenteService.trovaUtentePerId(userId)
                    .orElseThrow(() ->
                            new RuntimeException("Utente non trovato")
                    );

            // Utente disattivato = niente accesso
            if (!utente.getAttivo()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            SimpleGrantedAuthority authority =
                    new SimpleGrantedAuthority(
                            utente.getRuolo().name()
                    );

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            utente,
                            null,
                            List.of(authority)
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (Exception ex) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
        }
    }
}