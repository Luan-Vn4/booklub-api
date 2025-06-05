package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.domain.activities.clubactivities.MeetingDefinedActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeetingDefinedActivityMapper {

    @Mapping(source = "meeting.id", target = "meetingId")
    MeetingDefinedActivityDTO toDTO(MeetingDefinedActivity entity);
}