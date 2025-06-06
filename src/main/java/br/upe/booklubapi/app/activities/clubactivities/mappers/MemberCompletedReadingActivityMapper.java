package br.upe.booklubapi.app.activities.clubactivities.mappers;

import br.upe.booklubapi.app.activities.clubactivities.dtos.MemberCompletedReadingActivityDTO;
import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapper;
import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.clubactivities.MemberCompletedReadingActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberCompletedReadingActivityMapper
        extends ActivityDTOMapper<MemberCompletedReadingActivityDTO> {

    @Mapping(source = "bookUser.user.id", target = "userId")
    @Mapping(source = "bookUser.id.bookId", target = "bookId")
    @Mapping(source="club.id", target="clubId")
    MemberCompletedReadingActivityDTO toDTO(MemberCompletedReadingActivity entity);

    @Override
    default boolean canConvert(Activity activity) {
        return activity instanceof MemberCompletedReadingActivity;
    }

}