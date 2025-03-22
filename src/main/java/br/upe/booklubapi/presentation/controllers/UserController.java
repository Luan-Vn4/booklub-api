package br.upe.booklubapi.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(userService.create(user));
    }
}
