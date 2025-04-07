package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.KeycloakUserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = KeycloakUserDTOHelpers.class
)
public interface KeycloakUserDTOMapper {
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    KeycloakUserDTO toDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "imageUrl", source = "attributes", qualifiedByName = "userToUserImageUrl")
    User toEntity(KeycloakUserDTO dto);
}

@Component
@AllArgsConstructor
class KeycloakUserDTOHelpers {

    @Named("userToUserImageUrl")
    public String extractUserImageUrl(Map<String, List<String>> attributes) {
        if (attributes == null || !attributes.containsKey("image_url")) return null;
        return attributes.get("image_url").get(0);
    }

}