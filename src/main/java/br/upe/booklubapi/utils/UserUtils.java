package br.upe.booklubapi.utils;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.upe.booklubapi.presentation.exceptions.UserHasNoPermissionToException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserUtils {
    private JwtDecoder jwtDecoder;
    
	public String getUserToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();

		return request.getHeader("Authorization").substring(7);
	}

	public void verifyUserPermission(UUID idOfUserObjectReceivingChanges) {
		UUID requestIssuerId = getLoggedUserId();

		if (!requestIssuerId.equals(idOfUserObjectReceivingChanges)) {
			throw new UserHasNoPermissionToException("alterar usuário de id" + idOfUserObjectReceivingChanges);
		}
	}

    public UUID getLoggedUserId() {
        return UUID.fromString(jwtDecoder.decode(getUserToken()).getSubject());
    }

	public UUID getUserIdFromUserToken(String userToken) {
        return UUID.fromString(jwtDecoder.decode(userToken).getSubject());
    }
}
