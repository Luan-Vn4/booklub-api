package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.domain.activities.clubactivities.ReadingGoalDefinedActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReadingGoalDefinedActivityMapper {

    @Mapping(source = "readingGoal.id", target = "readingGoalId")
    ReadingGoalDefinedActivityDTO toDTO(ReadingGoalDefinedActivity entity);
}