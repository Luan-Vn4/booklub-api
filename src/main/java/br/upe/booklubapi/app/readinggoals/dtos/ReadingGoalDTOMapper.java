package br.upe.booklubapi.app.readinggoals.dtos;

import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ReadingGoalDTOMapper {

    @Mapping(
        target="clubId",
        expression="java(readingGoal.getClub().getId())"
    )
    ReadingGoalDTO toDto(ReadingGoal readingGoal);

}

