package br.upe.booklubapi.presentation.controllers.users;

import java.util.List;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.user.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Mono<UserDTO>> getUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getByUuid(uuid));
    }

    @GetMapping(params="email")
    public ResponseEntity<Mono<List<UserDTO>>> getUserByEmail(@PathParam("email") String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Mono<Void>> deleteUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.deleteById(uuid));
    }

    @PutMapping(value="/{uuid}", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Mono<Void>> updateUserById(@Valid @ModelAttribute UpdateUserDTO user, @PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.updateById(user, uuid));
    }
}
