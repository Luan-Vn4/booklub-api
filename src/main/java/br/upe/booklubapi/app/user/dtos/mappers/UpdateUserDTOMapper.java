package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import org.mapstruct.*;

@Mapper(
    componentModel=MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    uses={UserMediaStorageService.class},
    injectionStrategy=InjectionStrategy.CONSTRUCTOR
)
public abstract class UpdateUserDTOMapper {

    protected UserMediaStorageService userMediaStorageService;

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    public abstract User partialUpdate(
        UpdateUserDTO updateUserDTO,
        @MappingTarget User user
    );

    @AfterMapping
    protected void afterMapping(
        UpdateUserDTO updateUserDTO,
        @MappingTarget User user
    ) {
        if (updateUserDTO.image() == null) return;

        String imageUrl = userMediaStorageService.saveProfilePicture(
            updateUserDTO.image(),
            user.getId()
        );
        user.setImageUrl(imageUrl);
    }

}
