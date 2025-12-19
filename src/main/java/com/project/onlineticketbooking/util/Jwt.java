package com.project.onlineticketbooking.util;

import com.project.onlineticketbooking.user.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class Jwt {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateJwt(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).toList()
                )
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(getKey())
                .compact();
    }

    public String extractEmailFromToken(String token) {
        Key key = getKey();

        Claims claim = Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claim.getSubject();
    }
}
