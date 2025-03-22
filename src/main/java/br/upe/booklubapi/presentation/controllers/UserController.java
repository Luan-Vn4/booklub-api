package br.upe.booklubapi.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.DTOs.user.UserDTO;
import br.upe.booklubapi.app.services.UserServices.CreateUserService;
import br.upe.booklubapi.app.services.UserServices.DeleteUserService;
import br.upe.booklubapi.app.services.UserServices.GetUserService;
import br.upe.booklubapi.domain.entities.User;

@RestController
public class UserController {
    private final CreateUserService createUserService;
    private final DeleteUserService deleteUserService;
    private final GetUserService getUserService;

    public UserController(CreateUserService createUserService, DeleteUserService deleteUserService, GetUserService getUserService) {
        this.createUserService = createUserService;
        this.deleteUserService = deleteUserService;
        this.getUserService = getUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return createUserService.execute(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return deleteUserService.execute(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return getUserService.execute(id);
    }
}
