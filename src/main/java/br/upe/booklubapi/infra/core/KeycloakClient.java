package br.upe.booklubapi.infra.core;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import br.upe.booklubapi.utils.KeycloakUtils;
import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Qualifier;
import br.upe.booklubapi.config.KeycloakProperties;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;

import java.util.UUID;

@Component
@AllArgsConstructor
public class KeycloakClient {

    @Qualifier("keycloakWebClient")
    private final WebClient keycloakWebClient;

    private final KeycloakUtils keycloakUtils;

    private final KeycloakProperties keycloakProperties;

    public Mono<KeycloakUserDTO> getUserById(UUID uuid) {
        String adminToken = keycloakUtils.getAdminToken();

        return keycloakWebClient
                .get()
                .uri("/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users/" + uuid)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .bodyToMono(KeycloakUserDTO.class);
    }

    public Mono<List<KeycloakUserDTO>> getUserByEmail(String email) {
        String adminToken = keycloakUtils.getAdminToken();

        return keycloakWebClient
                .get()
                .uri("/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users?email=" + email)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .bodyToFlux(KeycloakUserDTO.class)
                .collectList();
    }

    public Mono<Void> deleteUserById(UUID uuid) {
        String adminToken = keycloakUtils.getAdminToken();

        return keycloakWebClient
                .delete()
                .uri("/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users/" + uuid)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> updateUserById(KeycloakUser updatedUser, UUID uuid) {
        String adminToken = keycloakUtils.getAdminToken();

        String user = "{"
                + "\"username\": \"" + updatedUser.getUsername() + "\","
                + "\"email\": \"" + updatedUser.getEmail() + "\","
                + "\"firstName\": \"" + updatedUser.getFirstName() + "\","
                + "\"lastName\": \"" + updatedUser.getLastName() + "\","
                + "\"enabled\": true,"
                + "\"attributes\": {"
                + "\"imageUrl\": [\"" + updatedUser.getAttributes().get("imageUrl").get(0) + "\"]"
                + "}"
                + "}";

        return keycloakWebClient
                .put()
                .uri("/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users/" + uuid)
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-Type", "application/json")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
