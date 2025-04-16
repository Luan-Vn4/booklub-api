package br.upe.booklubapi.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import br.upe.booklubapi.config.security.KeycloakProperties;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KeycloakUtils {

    private final KeycloakProperties keycloakProperties;

    public String getAdminToken() {
       JsonNode jsonToken =  WebClient.create()
                .post()
                .uri(keycloakProperties.getClientUrl() + "/realms/" + keycloakProperties.getClientRealm() +
                        "/protocol/openid-connect/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("client_secret=" + keycloakProperties.getClientSecret() +
                        "&grant_type=client_credentials&client_id=" + keycloakProperties.getClientId())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block()
                .get("access_token");

        if(jsonToken == null) {
            throw new RuntimeException("Falha na solicitação de Admin Token");
        }

        return jsonToken.asText();
    }
}
