package br.upe.booklubapi.presentation.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.auth.AuthService;
import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CreateUserDTO> register(@Valid @RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<KeycloakTokenDTO> login(@Valid @RequestBody AuthBody user) {
        return ResponseEntity.ok(authService.login(user));
    }
}
