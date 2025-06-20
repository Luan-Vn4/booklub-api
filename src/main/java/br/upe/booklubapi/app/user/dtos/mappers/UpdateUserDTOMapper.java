package br.upe.booklubapi.app.user.dtos.mappers;

import org.mapstruct.AfterMapping;
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
import br.upe.booklubapi.app.user.services.UserMediaStorageService;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = UserUpdateDTOMapperHelper.class
)
public interface UpdateUserDTOMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "image", ignore = true)
    User partialUpdate(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}


@Component
@AllArgsConstructor
class UserUpdateDTOMapperHelper {
    private final UserMediaStorageService userMediaStorageService;

    @AfterMapping
    public void mapImage(UpdateUserDTO dto, @MappingTarget User user) {
        if (dto.image() != null) {
            String imageUrl = userMediaStorageService.saveProfilePicture(dto.image(), user.getId());
            user.setImage(imageUrl);
        }
    }
}
