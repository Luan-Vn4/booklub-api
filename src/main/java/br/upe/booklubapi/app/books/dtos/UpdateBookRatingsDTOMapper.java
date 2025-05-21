package br.upe.booklubapi.app.books.dtos;

import br.upe.booklubapi.domain.books.entities.BookRatings;
import org.mapstruct.*;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class UpdateBookRatingsDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    public abstract BookRatings partialUpdate(
        BookRatingsDTO updateBookRatingsDTO,
        @MappingTarget BookRatings bookRatings
    );

}