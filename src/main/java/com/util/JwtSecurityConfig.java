package com.util;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtSecurityConfig {

    private final String SECRET = "MySecretKeyForJWTMySecretKeyForJWT";
    private final long EXPIRATION = 8600000;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(){
        return Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSigningKey()).compact();
    }

    public String getUserName(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException ex){
                return false;
        }
    }

}
