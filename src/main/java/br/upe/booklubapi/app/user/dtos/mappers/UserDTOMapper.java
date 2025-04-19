package br.upe.booklubapi.app.user.dtos.mappers;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;
import org.mapstruct.Named;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy= ReportingPolicy.IGNORE,
    uses=UserDTOMapperHelper.class
)
public interface UserDTOMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);

    @Named("fromJson")
    default UserDTO fromJson(JsonNode json) {
        UserDTO dto = new UserDTO(
            json.path("id").asText(),
            json.path("username").asText(),
            json.path("email").asText(),
            json.path("firstName").asText(),
            json.path("lastName").asText(),
            json.path("attributes").path("imageUrl").get(0).asText()
        );
        
        return dto;
    }
}

@Component
@AllArgsConstructor
class UserDTOMapperHelper {
    private UserMediaStorageService userMediaStorageService;

    @Named("imageUrltoAttributes")
    public Map<String, String> handleImageUrlMapping(UpdateUserDTO updateUserDTO) {
        if(updateUserDTO.image() == null) {
            return null;
        }
        String imagePath = userMediaStorageService.saveProfilePicture(updateUserDTO.image(), updateUserDTO.id());

        return Map.of("imageUrl", imagePath);
    }
}