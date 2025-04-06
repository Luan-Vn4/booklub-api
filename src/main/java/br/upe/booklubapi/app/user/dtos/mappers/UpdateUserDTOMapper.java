package br.upe.booklubapi.app.user.dtos.mappers;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = UpdateUserDTOMapperHelper.class
)
public interface UpdateUserDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    User partialUpdate(
        UpdateUserDTO updateUserDTO,
        @MappingTarget User user
    );
}

@Component
@AllArgsConstructor
class UpdateUserDTOMapperHelper {

    private final UserMediaStorageService userMediaStorageService;

    @AfterMapping
    public void afterMapping(
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
