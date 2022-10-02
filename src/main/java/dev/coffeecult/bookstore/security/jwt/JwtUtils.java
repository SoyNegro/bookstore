package dev.coffeecult.bookstore.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import dev.coffeecult.bookstore.security.service.BaseUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("coffeeCultDev.app.jwtSecret ")
    private String jwtSecret;
    @Value("coffeeCultDev.app.jwtExpirationTimeMs")
    private int jwtExpirationTimeMs;

    public String generateJWT(Authentication authentication) {
        BaseUserDetails userDetails = (BaseUserDetails) authentication.getPrincipal();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Instant.ofEpochSecond(new Date().getTime() + jwtExpirationTimeMs))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getUsernameFromJWT(String jwt) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtSecret)).build();
        return verifier.verify(jwt).getSubject();
    }


}
