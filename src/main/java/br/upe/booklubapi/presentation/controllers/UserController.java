package br.upe.booklubapi.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.DTOs.user.UserDTO;
import br.upe.booklubapi.app.services.UserServices.CreateUserService;
import br.upe.booklubapi.domain.entities.User;

@RestController
public class UserController {
    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return createUserService.execute(user);
    }
}
