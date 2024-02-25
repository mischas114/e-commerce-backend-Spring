package com.example.ecommercebackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ecommercebackend.model.LokalerUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Ein Service, der die Verwaltung von JWTs ermöglicht.
 */
@Service
public class JWTService {

    @Value("${jwt.algorithmus.key}")
    private String AlgorithmusKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_CLAIM = "username";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(AlgorithmusKey);
    }

    /**
     * Generiert einen JWT für den gegebenen Benutzer.
     *
     * @param user Der Benutzer, für den ein JWT generiert werden soll.
     * @return Der generierte JWT.
     */

    public String generateJWT(LokalerUser user) {
        return JWT.create()
                .withClaim("username", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryInSeconds * 1000L))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}