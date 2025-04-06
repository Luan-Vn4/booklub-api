package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    uses={CreateUserDTOMapperHelper.class}
)
public interface CreateUserDTOMapper {

    User toEntity(CreateUserDTO createUserDTO);

}

@Component
@AllArgsConstructor
class CreateUserDTOMapperHelper {

    private final UserMediaStorageService userMediaStorageService;

    @AfterMapping
    public void afterMapping(
        CreateUserDTO createUserDTO,
        @MappingTarget User user
    ) {
        UUID userId = user.getId();
        String imageUrl = userMediaStorageService.saveProfilePicture(
            createUserDTO.image(),
            userId
        );
        user.setId(userId);
        user.setImageUrl(imageUrl);
    }

}
