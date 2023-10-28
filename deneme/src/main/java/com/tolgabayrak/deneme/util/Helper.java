package com.tolgabayrak.deneme.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Helper {

    private static final long accessTokenValidityMilliseconds = 15 * 60 * 1000;

    private static final long refreshTokenValidityMilliseconds = 30L * 24 * 60 * 60 * 1000;

    public String generateAccessToken(String subject, Long id) {
        long now = System.currentTimeMillis();
        Date expirationDate = new Date(now + accessTokenValidityMilliseconds);

        return Jwts.builder()
                .setSubject(subject)
                .claim("name", "Tolga")
                .claim("id", id)
                .setIssuedAt(new Date(now))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "secret_key".getBytes())
                .compact();
    }

    public String generateRefreshToken(String subject) {
        long now = System.currentTimeMillis();
        Date expirationDate = new Date(now + refreshTokenValidityMilliseconds);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,"secret_key".getBytes())
                .compact();
    }

}
