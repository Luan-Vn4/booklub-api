package br.upe.booklubapi.app.activities.clubactivities.mappers;

import br.upe.booklubapi.app.activities.clubactivities.dtos.ReadingGoalDefinedActivityDTO;
import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapper;
import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.clubactivities.ReadingGoalDefinedActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReadingGoalDefinedActivityMapper
        extends ActivityDTOMapper<ReadingGoalDefinedActivityDTO> {

    @Mapping(source = "readingGoal.id", target = "readingGoalId")
    @Mapping(source="club.id", target="clubId")
    ReadingGoalDefinedActivityDTO mapToDTO(ReadingGoalDefinedActivity entity);

    @Override
    default ReadingGoalDefinedActivityDTO toDTO(Activity activity) {
        if (!canConvert(activity)) throw new IllegalArgumentException(
            "Cannot convert activity to ReadingGoalDefinedActivityDTO"
        );

        return mapToDTO((ReadingGoalDefinedActivity) activity);
    }

    @Override
    default boolean canConvert(Activity activity) {
        return activity instanceof ReadingGoalDefinedActivity;
    }

}