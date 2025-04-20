package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ClubPendingEntryDTOMapper {

    @Mapping(target="userId", source="user.id")
    @Mapping(target="clubId", source="club.id")
    ClubPendingEntryDTO toDto(ClubPendingEntry clubPendingEntry);



}
