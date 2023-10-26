package com.example.tokenguard.utils;

import com.example.tokenguard.domain.User;
import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String jwtSecret;
    private final long jwtExpirationInMs = 3600000; // 1 hour

    public String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims) // setting the claims
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            System.out.println(claims);
            return claims;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired");
        }
    }

}
