package br.upe.booklubapi.app.auth;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.upe.booklubapi.app.auth.dto.AuthBody;
import br.upe.booklubapi.app.auth.dto.KeycloakTokenDTO;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.config.KeycloakProperties;
import br.upe.booklubapi.utils.KeycloakUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private KeycloakProperties keycloakProperties;
    private KeycloakUtils keycloakUtils;

    @Override
    public CreateUserDTO register(CreateUserDTO userDTO) {
        String adminToken = keycloakUtils.getAdminToken();

        String userJson = "{"
                + "\"username\": \"" + userDTO.username() + "\","
                + "\"email\": \"" + userDTO.email() + "\","
                + "\"firstName\": \"" + userDTO.firstName() + "\","
                + "\"lastName\": \"" + userDTO.lastName() + "\","
                + "\"enabled\": true,"
                + "\"credentials\": [{"
                + "\"type\": \"password\","
                + "\"value\": \"" + userDTO.password() + "\","
                + "\"temporary\": false"
                + "}],"
                + "\"attributes\": {"
                + "\"imageUrl\": \"" + userDTO.imageUrl() + "\""
                + "}"
                + "}";

        WebClient
                .create(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users")
                .post()
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-Type", "application/json")
                .bodyValue(userJson)
                .retrieve()
                .toBodilessEntity()
                .block();

        return userDTO;
    }

    @Override
    public KeycloakTokenDTO login(AuthBody user) {
        WebClient client = WebClient.builder().build();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", keycloakProperties.getClientId());
        formData.add("client_secret", keycloakProperties.getClientSecret());
        formData.add("username", user.username());
        formData.add("password", user.password());
        formData.add("grant_type", "password");

        String tokenUrl = keycloakProperties.getClientUrl() + "/realms/" + keycloakProperties.getClientRealm()
                + "/protocol/openid-connect/token";

        KeycloakTokenDTO token = client.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(KeycloakTokenDTO.class)
                .block();

        return token;
    }
}
