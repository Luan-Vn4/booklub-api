package br.upe.booklubapi.presentation.controllers.users;

import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> createUser(
        @Valid
        @ModelAttribute
        CreateUserDTO user
    ) {
        return ResponseEntity.ok(userService.create(user));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getByUuid(uuid));
    }

    @GetMapping(params="email")
    public ResponseEntity<UserDTO> getUserByEmail(@PathParam("email") String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PutMapping(value="/{uuid}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> updateUser(
        @Valid
        @ModelAttribute
        UpdateUserDTO updateUserDTO,
        @PathVariable
        UUID uuid
    ) {
        return ResponseEntity.ok(userService.update(updateUserDTO, uuid));
    }

}
