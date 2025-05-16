package org.example.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String jwtSecret;
    @Value("${security.jwt.expiration-time}")
    private long expirationTime;
    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    // Генерация JWT
    public String generateJwt(String user) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationTime);
        Date refreshExp = new Date(now.getTime() + refreshExpirationTime);

        return Jwts.builder()
//                .setSubject(user)
//                .setIssuedAt(now)
//                .setExpiration(exp)
                .claim("subject", user)
                .claim("issuedAt", now)
                .claim("expiration", exp)
                .claim("refreshmentExpiration", refreshExp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String refreshJwt(String user, Date refreshExp) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
//                .setSubject(user)
//                .setIssuedAt(now)
//                .setExpiration(exp)
                .claim("subject", user)
                .claim("issuedAt", now)
                .claim("expiration", exp)
                .claim("refreshmentExpiration", refreshExp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
