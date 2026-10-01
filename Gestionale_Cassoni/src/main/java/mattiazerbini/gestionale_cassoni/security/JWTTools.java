package mattiazerbini.gestionale_cassoni.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    private static final long EXPIRATION_TIME =
            1000L * 60 * 60 * 12;

    public String generateToken(Utente utente) {

        Date now = new Date();

        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(utente.getId().toString())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public void verifyToken(String token) {

        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token);
    }

    public Long extractIdFromToken(String token) {

        return Long.parseLong(Jwts.parser().verifyWith(
                                Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject());
    }
}