package com.spotify.rest.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.rest.Model.User;
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    private ObjectMapper objectMapper = new ObjectMapper();
    public String generateToken(User user) {

        Map<String, Object> claims = objectMapper.convertValue(user, Map.class);
        claims.remove("password");
        return Jwts.builder()
                    .claims(claims)
                    .subject(claims.get("userId").toString())
                    .issuedAt(new Date())
                    .signWith(key)
                    .compact();
    }
}
