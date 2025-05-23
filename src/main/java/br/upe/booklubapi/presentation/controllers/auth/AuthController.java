package br.upe.booklubapi.presentation.controllers.auth;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.auth.AuthService;
import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.AuthResponseDTO;
import br.upe.booklubapi.app.auth.dto.UpdateUserPasswordDTO;
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
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthBody user) {
        return ResponseEntity.ok(authService.login(user));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Mono<Void>> deleteUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(authService.deleteById(uuid));
    }

    @PutMapping
    public ResponseEntity<Mono<Void>> updateUserPasswordById(@Valid @RequestBody UpdateUserPasswordDTO dto) {
        return ResponseEntity.ok(authService.updateUserPassword(dto));
    }
}
