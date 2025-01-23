package com.intech.dev.intweet.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JWTUtils {
    private static final String SECRET_KEY = "vxW7yvH6kXXZWv0KDgYw8RQkcQ36qhgmWLEEAarXXZWv0KDgYw8RQkc6qhgmWLEEEEAarXXZWv";

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Encoders.BASE64.encode(SECRET_KEY.getBytes()).getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(String username, String sessionId) {
        return Jwts.builder()
                .claim("username", username)
                .claim("sessionId", sessionId)
                .signWith(getSigningKey())
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username")
                .toString();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: " + e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: " + e.getMessage());
            return false;
        }
        return true;
    }

}
