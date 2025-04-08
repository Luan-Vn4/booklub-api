package br.upe.booklubapi.presentation.controllers.users;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<KeycloakUserDTO> getUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getByUuid(uuid));
    }

    @GetMapping(params="email")
    public ResponseEntity<List<KeycloakUserDTO>> getUserByEmail(@PathParam("email") String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID uuid) {
        userService.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<KeycloakUserDTO> updateUserById(@Valid @RequestBody UpdateUserDTO user, @PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.updateById(user, uuid));
    }
}
