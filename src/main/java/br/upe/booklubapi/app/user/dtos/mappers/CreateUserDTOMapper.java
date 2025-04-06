package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import org.mapstruct.*;
import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import java.util.UUID;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    uses={UserMediaStorageService.class},
    injectionStrategy=InjectionStrategy.CONSTRUCTOR
)
public abstract class CreateUserDTOMapper {

    private UserMediaStorageService userMediaStorageService;

    public abstract User toEntity(CreateUserDTO createUserDTO);

    @AfterMapping
    protected void afterMapping(
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
