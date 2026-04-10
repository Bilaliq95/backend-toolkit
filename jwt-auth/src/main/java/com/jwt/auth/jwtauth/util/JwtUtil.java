package com.jwt.auth.jwtauth.util;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "thisisaverylongsecretkeyforjwttoken1234567890";

    public static String generateToken(String subject) {
        // Step 1: create builder
        JwtBuilder builder = Jwts.builder();

        // Step 2: set subject (who the token belongs to)
        builder.setSubject(subject);

        // Step 3: set current time
        builder.setIssuedAt(new Date());

        // Step 4: set expiry (1 hour later)
        Date expiry = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
        builder.setExpiration(expiry);

        // Step 5: sign the token with secret
        builder.signWith(SignatureAlgorithm.HS256, SECRET);

        // Step 6: generate final token string
        String token = builder.compact();

        return token;
    }

    public static String extractSubject(String token) {

        // Step 1: create parser
        JwtParser parser = Jwts.parser();

        // Step 2: set secret key (used to verify token signature)
        parser.setSigningKey(SECRET);

        // Step 3: parse token (validates + decodes)
        Jws<Claims> parsed = parser.parseClaimsJws(token);

        // Step 4: get payload (data inside token)
        Claims body = parsed.getBody();

        // Step 5: extract subject (user identity)
        String subject = body.getSubject();

        return subject;
    }
}