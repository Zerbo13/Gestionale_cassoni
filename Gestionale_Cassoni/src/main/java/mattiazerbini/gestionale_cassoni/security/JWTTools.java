package mattiazerbini.gestionale_cassoni.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Utente utente) {

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)
                )
                .subject(String.valueOf(utente.getId()))
                .claim("ruolo", utente.getRuolo().name())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {

        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token);
    }

    public Long extractIdFromToken(String token) {

        return Long.parseLong(
                Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }
}