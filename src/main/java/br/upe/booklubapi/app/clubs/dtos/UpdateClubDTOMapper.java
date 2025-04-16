package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.domain.clubs.entities.Club;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class UpdateClubDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    public abstract Club partialUpdate(
        UpdateClubDTO updateClubDTO,
        @MappingTarget Club club
    );

}