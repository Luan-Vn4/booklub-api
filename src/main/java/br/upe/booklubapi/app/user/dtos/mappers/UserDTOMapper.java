package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import org.springframework.stereotype.Component;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy= ReportingPolicy.IGNORE,
    uses={UserDTOMapperHelper.class}
)
public interface UserDTOMapper {

    @Mapping(
        target="imageUrl",
        qualifiedByName="resolveImageUrl"
    )
    UserDTO toDto(User user);

}

@Component
@AllArgsConstructor
class UserDTOMapperHelper {

    private final UserMediaStorageService userMediaStorageService;

    @Named("resolveImageUrl")
    public String resolveImageUrl(String profilePicPath) {
        return userMediaStorageService.getProfilePictureUrl(profilePicPath);
    }

}
