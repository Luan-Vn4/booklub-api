package br.upe.booklubapi.presentation.controllers.auth;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value="/register", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Mono<Void>> register(@Valid @ModelAttribute CreateUserDTO user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Mono<KeycloakTokenDTO>> login(@Valid @RequestBody AuthBody user) {
        return ResponseEntity.ok(authService.login(user));
    }
}
