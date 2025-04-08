package br.upe.booklubapi.app.user.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.config.KeycloakProperties;
import br.upe.booklubapi.presentation.exceptions.UserHasNoPermissionToException;
import br.upe.booklubapi.presentation.exceptions.UserNotFoundException;
import br.upe.booklubapi.utils.KeycloakUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final KeycloakProperties keycloakProperties;
    private final KeycloakUtils keycloakUtils;
    JwtDecoder jwtDecoder;

    @Override
    public KeycloakUserDTO getByUuid(UUID uuid) {
        String adminToken = keycloakUtils.getAdminToken();

        KeycloakUserDTO userDTO = WebClient.create()
                .get()
                .uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users/" + uuid)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .bodyToMono(KeycloakUserDTO.class)
                .block();

        return userDTO;
    }

    @Override
    public List<KeycloakUserDTO> getByEmail(String email) {
        String adminToken = keycloakUtils.getAdminToken();

        List<KeycloakUserDTO> users = WebClient.create()
                .get()
                .uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users?email=" + email)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .bodyToFlux(KeycloakUserDTO.class)
                .collectList()
                .block();

        return users;
    }

    @Override
    public void deleteById(UUID uuid) {
        String userToken = getUserToken();
        String requestIssuerId = jwtDecoder.decode(userToken).getSubject();
		String adminToken = keycloakUtils.getAdminToken();

        if(!requestIssuerId.equals(uuid.toString())) {
			throw new UserHasNoPermissionToException("deletar usuáiro de id" + uuid);
        }

        WebClient.create()
                .delete()
                .uri(keycloakProperties.getClientUrl() + "/admin/realms/" + keycloakProperties.getClientRealm()
                        + "/users/" + uuid)
                .header("Authorization", "Bearer " + adminToken)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public String getUserToken() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes()).getRequest();

    return request.getHeader("Authorization").substring(7);
}

}
