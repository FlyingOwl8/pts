package org.example.service.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.security.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.security.AuthorizationToken;
import org.example.data.entity.security.User;
import org.example.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthTokenValidationService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    public AuthorizationToken decodeToken(String token) {
        try {

        SignatureAlgorithm sa = SignatureAlgorithm.HS512;
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, sa.getJcaName());
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());

            // Парсим токен
            Claims claims = Jwts.parser()
                    .verifyWith(secretKeySpec)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // Преобразуем Claims в AuthorizationToken
            AuthorizationToken authorizationToken = new AuthorizationToken();
            authorizationToken.setSubject(Integer.valueOf(claims.get("subject", String.class)));
            authorizationToken.setIssuedAt(claims.get("issuedAt", Date.class));
            authorizationToken.setExpiration(claims.get("expiration", Date.class));
            authorizationToken.setRefreshmentExpiration(claims.get("refreshmentExpiration", Date.class));

            return authorizationToken;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
