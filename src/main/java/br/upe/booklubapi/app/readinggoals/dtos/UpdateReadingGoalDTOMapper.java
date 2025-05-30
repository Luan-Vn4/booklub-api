package br.upe.booklubapi.app.readinggoals.dtos;

import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UpdateReadingGoalDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    ReadingGoal partialUpdate(
        UpdateReadingGoalDTO updateReadingGoalDTO,
        @MappingTarget ReadingGoal readingGoal
    );

}
