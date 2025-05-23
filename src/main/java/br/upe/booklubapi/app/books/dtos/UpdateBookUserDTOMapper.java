package br.upe.booklubapi.app.books.dtos;

import br.upe.booklubapi.domain.books.entities.BookUser;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class UpdateBookUserDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    public abstract BookUser partialUpdate(
        BookUserDTO updateBookUserDTO,
        @MappingTarget BookUser bookUser
    );

}