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
    @Mapping(source="club.id", target="")
    MeetingDefinedActivityDTO toDTO(MeetingDefinedActivity entity);

    @Override
    default boolean canConvert(Activity activity) {
        return activity instanceof MeetingDefinedActivity;
    }

}