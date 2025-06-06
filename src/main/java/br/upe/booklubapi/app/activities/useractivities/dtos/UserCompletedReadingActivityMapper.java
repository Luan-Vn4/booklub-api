package br.upe.booklubapi.app.activities.useractivities.dtos;

import br.upe.booklubapi.domain.activities.entities.useractivities.UserCompletedReadingActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCompletedReadingActivityMapper {

    @Mapping(source = "bookUser.user.id", target = "userId")
    @Mapping(source = "bookUser.id.bookId", target = "bookId")
    UserCompletedReadingActivityDTO toDTO(UserCompletedReadingActivity entity);
}