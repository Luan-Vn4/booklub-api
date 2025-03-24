package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.domain.clubs.entities.Club;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UpdateClubDTOMapper {

    Club toEntity(UpdateClubDTO updateClubDTO);

    UpdateClubDTO toDto(Club club);

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    Club partialUpdate(UpdateClubDTO updateClubDTO, @MappingTarget Club club);

}