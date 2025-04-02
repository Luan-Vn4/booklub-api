package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.app.clubs.services.ClubMediaStorageService;
import br.upe.booklubapi.domain.clubs.entities.Club;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {UpdateClubDTOMapperHelpers.class}
)
public abstract class UpdateClubDTOMapper {

    @Autowired
    protected UpdateClubDTOMapperHelpers updateClubDTOMapperHelpers;

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(
        target="imageUrl",
        expression=("""
            java(updateClubDTOMapperHelpers.imageToImageUrl(
                updateClubDTO.image(), updateClubDTO.name()
            ))
        """)
    )
    public abstract Club partialUpdate(UpdateClubDTO updateClubDTO, @MappingTarget Club club);

    @AfterMapping
    public void afterMapToClub(@MappingTarget Club club) {
        updateClubDTOMapperHelpers.call();
    }

}

@Component
@AllArgsConstructor
class UpdateClubDTOMapperHelpers {

    private final ClubMediaStorageService clubMediaStorageService;

    public void call() {}

    @Named("imageToImageUrl")
    public String imageToImageUrl(MultipartFile image, String name) {
        return clubMediaStorageService.saveClubPicture(image, name);
    }

}