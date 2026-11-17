package com.example.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String username, String email) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .claim("email", email)
                .issuedAt(new Date(now))
                .expiration(new Date(now + 24 * 60 * 60 * 1000L))
                .signWith(key)
                .compact();
    }
}
