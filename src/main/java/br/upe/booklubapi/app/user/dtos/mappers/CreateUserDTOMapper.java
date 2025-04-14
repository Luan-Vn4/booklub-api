package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.KeycloakUser;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    uses=CreateUserDTOMapperHelper.class
)

public interface CreateUserDTOMapper {
    // @Mapping(source="image", source="username", target= "attributes", qualifiedByName ="imageUrltoAttributes")
    KeycloakUser toKeycloakUser(CreateUserDTO createUserDTO);
}

// @Component
// @AllArgsConstructor
// class CreateUserDTOMapperHelper {
//     UserMediaStorageService userMediaStorageService;

//     @Named("imageUrltoAttributes")
//     public Map<String, List<String>> handleImageUrlMapping(MultipartFile image, String username) {
//         String imagePath = userMediaStorageService.saveProfilePicture(image, username);
//     }

// }
