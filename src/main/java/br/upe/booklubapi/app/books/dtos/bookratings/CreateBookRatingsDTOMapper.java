package br.upe.booklubapi.app.books.dtos.bookratings;

import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.exceptions.UserNotFoundException;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={CreateBookRatingsDTOMapperHelpers.class}
)
public interface CreateBookRatingsDTOMapper {

    BookRatings toEntity(
        CreateBookRatingsDTO dto,
        @Context
        BookUserId bookUserId
    );

}

@Component
@AllArgsConstructor
class CreateBookRatingsDTOMapperHelpers {

    private final UserRepository userRepository;

    @AfterMapping
    public void afterMapping(
        CreateBookRatingsDTO dto,
        @MappingTarget
        BookRatings bookRatings,
        @Context
        BookUserId bookUserId
    ) {
        final User user = userRepository.findById(bookUserId.getUserId())
            .orElseThrow(
                () -> new UserNotFoundException(bookUserId.getUserId())
            );

        bookRatings.setId(bookUserId);
        bookRatings.setUser(user);
    }

}