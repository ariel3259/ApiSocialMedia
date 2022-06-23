package com.ariel.ApiSocialMedia.Utils.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenManager {
    
    @Autowired
    private Key key;

    private static long TOKEN_VALIDITY = 1800;

    public String generateToken(UserDetails userDetail){
        Map<String, Object> claims  = new HashMap<>();

        return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDetail.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                    .signWith(key).compact();
    }

    public boolean validateToken(UserDetails userDetail, String token){
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        boolean isExpired = claims.getExpiration().before(new Date());
        return ((userDetail.getUsername().equals(username)) && !isExpired);
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
