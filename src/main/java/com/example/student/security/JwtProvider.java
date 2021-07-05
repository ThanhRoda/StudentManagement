package com.example.student.security;

import com.example.student.models.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtProvider {
    private final String SECRET_KEY = "123abc";

    public String generateToken(User user) {
        return  Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(Long.toString(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10) )
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    private Boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }

    public boolean tokenValidation(String token) {
        try  {
           Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
//            final Long id = getUserIdFromToken(token);
//            return (id.equals(user.getId()) && !isTokenExpired(token));

        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
