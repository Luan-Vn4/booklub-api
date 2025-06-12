package br.upe.booklubapi.app.books.dtos.bookratings;

import br.upe.booklubapi.domain.books.entities.BookRatings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface BookRatingsDTOMapper {

    @Mapping(source="id.bookId", target="bookId")
    @Mapping(source="id.userId", target="userId")
    BookRatingsDTO toDTO(BookRatings entity);

}
