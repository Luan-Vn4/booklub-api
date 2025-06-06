package br.upe.booklubapi.app.activities.clubactivities.dtos;

import br.upe.booklubapi.domain.activities.entities.clubactivities.MemberCompletedReadingActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberCompletedReadingActivityMapper {

    @Mapping(source = "bookUser.user.id", target = "userId")
    @Mapping(source = "bookUser.id.bookId", target = "bookId")
    MemberCompletedReadingActivityDTO toDTO(MemberCompletedReadingActivity entity);
}