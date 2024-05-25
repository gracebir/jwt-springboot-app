package com.grace.jwt_authentication.config;

import com.grace.jwt_authentication.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    @Value("${security.jwt.expiration.minutes}")
    private Long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret.key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Objects> extraClaims){
        Date issueAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issueAt.getTime() + (EXPIRATION_MINUTES * 1000));
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .signWith(generatekey(), SignatureAlgorithm.ES256)
                .compact();

    }

    private Key generatekey() {
        byte[] secreteAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secreteAsBytes);
    }
}
