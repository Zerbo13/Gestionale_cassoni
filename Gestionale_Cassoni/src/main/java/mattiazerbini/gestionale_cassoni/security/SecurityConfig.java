package mattiazerbini.gestionale_cassoni.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JWTCheckedFilter jwtCheckedFilter,
            RateLimitFilter rateLimitFilter
    ) throws Exception {

        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.formLogin(
                formLogin -> formLogin.disable()
        );

        httpSecurity.httpBasic(
                httpBasic -> httpBasic.disable()
        );

        httpSecurity.csrf(
                csrf -> csrf.disable()
        );

        httpSecurity.sessionManagement(
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        );

        httpSecurity.authorizeHttpRequests(auth -> auth

                // LOGIN
                .requestMatchers(
                        "/auth/**",
                        "/error"
                ).permitAll()


                // LUOGHI

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/luoghi/**"
                ).authenticated()

                .requestMatchers(
                        HttpMethod.POST,
                        "/api/luoghi/**"
                ).hasAuthority("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/luoghi/**"
                ).hasAuthority("ADMIN")


                // MEZZI

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/mezzi/**"
                ).authenticated()

                .requestMatchers(
                        HttpMethod.POST,
                        "/api/mezzi/**"
                ).hasAuthority("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/mezzi/**"
                ).hasAuthority("ADMIN")


                // CASSONI

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/cassoni/**"
                ).authenticated()

                .requestMatchers(
                        HttpMethod.POST,
                        "/api/cassoni/**"
                ).hasAuthority("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/cassoni/**"
                ).hasAuthority("ADMIN")


                // UTENTI

                .requestMatchers(
                        "/api/utenti/**"
                ).hasAuthority("ADMIN")


                // VIAGGI

                // Tutti i viaggi -> solo ADMIN
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/viaggi"
                ).hasAuthority("ADMIN")

                // Viaggi di oggi -> solo ADMIN
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/viaggi/oggi"
                ).hasAuthority("ADMIN")

                // Viaggi dell'utente autenticato
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/viaggi/miei"
                ).authenticated()

                // Storico viaggi di un cassone
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/viaggi/cassone/**"
                ).authenticated()

                // Avvio viaggio
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/viaggi/**"
                ).hasAnyAuthority(
                        "OPERAIO",
                        "ADMIN"
                )

                // Chiusura / annullamento viaggio
                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/viaggi/**"
                ).hasAnyAuthority(
                        "OPERAIO",
                        "ADMIN"
                )


                // QUALSIASI ALTRA RICHIESTA
                .anyRequest().authenticated()
        );

        // RATE LIMIT
        httpSecurity.addFilterBefore(
                rateLimitFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        httpSecurity.addFilterAfter(
                jwtCheckedFilter,
                RateLimitFilter.class
        );

        return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173",
                        "http://localhost:5174",
                        "https://gestionale-cassoni.vercel.app",
                        "https://gestionale.mctrasportisrl.com"
                )
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}
