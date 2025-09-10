package com.infinitix.comandapro_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "w8FjKp2sQv6xZr9tLm3bNf5hTg1uVw4yPq7eSd0aXc2lBv8nR";

    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(String username, Long restaurantId, long expiration) {
        return Jwts.builder()
                .subject(username)
                .claim("restaurantId", restaurantId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public static String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public static Long extractRestaurantId(String token) {
        Object id = extractAllClaims(token).get("restaurantId");
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        } else if (id instanceof Long) {
            return (Long) id;
        } else if (id instanceof String) {
            return Long.valueOf((String) id);
        }
        return null;
    }

    public static boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}