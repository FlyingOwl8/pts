package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.responses.LoginResponse;
import org.example.data.dto.security.AuthorizationToken;
import org.example.data.dto.security.LoginUserDTO;
import org.example.service.UserService;
import org.example.service.security.AuthTokenValidationService;
import org.example.service.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthTokenValidationService authTokenValidationService;

    // Аутентификация пользователя
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserDTO authRequest) {
        try {
            boolean isAuthenticated = userService.authenticate(authRequest.getEmail(), authRequest.getPassword());

            if (isAuthenticated) {
                // Если аутентификация успешна, генерируем JWT
                String jwt = jwtService.generateJwt(
                        String.valueOf(userService.findUserByUsername(authRequest.getEmail()).getId())
                );
                return ResponseEntity.ok(new LoginResponse(jwt));
            } else {
                throw new RuntimeException("Пользователь с переданными данными не найден");
            }
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody String accessToken) {
        try {
            AuthorizationToken token = authTokenValidationService.decodeToken(accessToken);
            String jwt = jwtService.refreshJwt(
                    String.valueOf(token.getSubject()), token.getRefreshmentExpiration()
            );
            return ResponseEntity.ok(new LoginResponse(jwt));
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(e.getMessage());
        }
    }
}
