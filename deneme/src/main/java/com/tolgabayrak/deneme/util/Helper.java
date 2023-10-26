package com.tolgabayrak.deneme.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Helper {

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;


    public String generateAccessToken(Object payload) {
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("key1", "value1") // Özelleştirilmiş payload verilerini ekleyebilirsiniz.
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String generateRefreshToken(Object payload) {
        try {
            // Burada farklı bir algoritma kullanabilirsiniz, örnek olarak HS256:
            Algorithm algorithm = Algorithm.HMAC256("your-secret-refresh-token-key");
            String refreshToken = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("key2", "value2") // Özelleştirilmiş payload verilerini ekleyebilirsiniz.
                    .sign(algorithm);
            return refreshToken;
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
