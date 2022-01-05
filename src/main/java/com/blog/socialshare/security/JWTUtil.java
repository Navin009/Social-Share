package com.blog.socialshare.security;

import org.springframework.beans.factory.annotation.Value;

public class JWTUtil {

    public static final long EXPIRATION_TIME = 60 * 60 * 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return null;
    }

    public String generateToken(String username) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }
}