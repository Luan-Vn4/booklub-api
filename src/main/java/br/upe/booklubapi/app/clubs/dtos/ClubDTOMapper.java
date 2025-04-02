package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.app.clubs.services.ClubMediaStorageService;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.users.entities.User;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={ClubDTOMapperHelpers.class}
)
public interface ClubDTOMapper {

    @Mapping(target="ownerId", source="owner", qualifiedByName="ownerToOwnerId")
    @Mapping(
        target="imageUrl",
        source="imageUrl",
        qualifiedByName="resolveImageUrl"
    )
    ClubDTO toDto(Club club);

}

@Component
@AllArgsConstructor
class ClubDTOMapperHelpers {

    private final ClubMediaStorageService clubMediaStorageService;

    @Named("ownerToOwnerId")
    public UUID ownerToOwnerId(User owner) {
        return owner.getId();
    }

    @Named("resolveImageUrl")
    public String resolveImageUrl(String imageUrl) {
        return clubMediaStorageService.getClubPictureUrl(imageUrl);
    }

}
