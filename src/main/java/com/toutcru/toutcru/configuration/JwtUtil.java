package com.toutcru.toutcru.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private Long expiration;

    public String generateToken(String email) {
        Map<String, Object>claims = new HashMap<>();
        claims.put("email", email);

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

}
