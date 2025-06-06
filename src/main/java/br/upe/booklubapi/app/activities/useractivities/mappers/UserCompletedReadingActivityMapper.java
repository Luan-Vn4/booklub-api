package br.upe.booklubapi.app.activities.useractivities.mappers;

import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapper;
import br.upe.booklubapi.app.activities.useractivities.dtos.UserCompletedReadingActivityDTO;
import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.activities.entities.useractivities.UserCompletedReadingActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCompletedReadingActivityMapper
        extends ActivityDTOMapper<UserCompletedReadingActivityDTO> {

    @Mapping(source = "bookUser.user.id", target = "userId")
    @Mapping(source = "bookUser.id.bookId", target = "bookId")
    UserCompletedReadingActivityDTO toDTO(UserCompletedReadingActivity entity);


    @Override
    default boolean canConvert(Activity activity) {
        return activity instanceof UserCompletedReadingActivity;
    }

}