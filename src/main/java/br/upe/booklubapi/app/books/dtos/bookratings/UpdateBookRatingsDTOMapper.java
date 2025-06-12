package br.upe.booklubapi.app.books.dtos.bookratings;

import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.users.exceptions.UserNotFoundException;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = UpdateBookRatingsDTOMapperHelpers.class
)
public interface UpdateBookRatingsDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    BookRatings partialUpdate(
        UpdateBookRatingsDTO dto,
        @MappingTarget BookRatings bookRatings,
        @Context UUID userId,
        @Context String bookId
    );

}

@Component
@AllArgsConstructor
class UpdateBookRatingsDTOMapperHelpers {

    private final UserRepository userRepository;

    @AfterMapping
    public void afterMappinng(
        UpdateBookRatingsDTO dto,
        @MappingTarget BookRatings bookRatings,
        @Context UUID userId,
        @Context String bookId
    ) {
        bookRatings.setId(new BookUserId(bookId, userId));
        bookRatings.setUser(
            userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
            )
        );
    }

}