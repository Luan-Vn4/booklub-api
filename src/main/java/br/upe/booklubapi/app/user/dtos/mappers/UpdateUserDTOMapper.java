package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.Map;
import java.util.UUID;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.infra.core.KeycloakClient;
import lombok.AllArgsConstructor;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses=UpdateUserDTOHelper.class
)
public interface UpdateUserDTOMapper {
    @BeanMapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="updateUserDTO", target="attributes", qualifiedByName="imageUrltoAttributes")
    User partialUpdate(UpdateUserDTO updateUserDTO, @MappingTarget User user);
    
}

@Component
@AllArgsConstructor
class UpdateUserDTOHelper {
    private UserMediaStorageService userMediaStorageService;
    private KeycloakClient keycloakClient;

    @Named("imageUrltoAttributes")
    public Map<String, String> handleImageUrlMapping(UpdateUserDTO updateUserDTO) {

        UserDTO userDTO = keycloakClient.getUserByEmail(updateUserDTO.email()).block().get(0);

        String imagePath = userMediaStorageService.saveProfilePicture(updateUserDTO.image(), UUID.fromString(userDTO.id()));

        return Map.of("imageUrl", imagePath);
    }

}
