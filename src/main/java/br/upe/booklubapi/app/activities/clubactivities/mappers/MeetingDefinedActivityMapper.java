package br.upe.booklubapi.app.activities.clubactivities.mappers;

import br.upe.booklubapi.app.activities.clubactivities.dtos.MeetingDefinedActivityDTO;
import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapper;
import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.clubactivities.MeetingDefinedActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeetingDefinedActivityMapper
        extends ActivityDTOMapper<MeetingDefinedActivityDTO> {

    @Mapping(source="meeting.id", target="meetingId")
    @Mapping(source="club.id", target="clubId")
    MeetingDefinedActivityDTO mapToDTO(MeetingDefinedActivity entity);

    @Override
    default MeetingDefinedActivityDTO toDTO(Activity activity) {
        if (!canConvert(activity)) throw new IllegalArgumentException(
            "Cannot convert activity to MeetingDefinedActivityDTO"
        );

        return mapToDTO((MeetingDefinedActivity) activity);
    }

    @Override
    default boolean canConvert(Activity activity) {
        return activity instanceof MeetingDefinedActivity;
    }

}