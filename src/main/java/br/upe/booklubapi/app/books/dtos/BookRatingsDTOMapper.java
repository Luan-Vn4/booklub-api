package br.upe.booklubapi.app.books.dtos;

import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import java.util.UUID;

import br.upe.booklubapi.domain.books.entities.BookRatings;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={BookRatingsDTOMapperHelpers.class}
)
public interface BookRatingsDTOMapper {

    @Mapping(source="bookId", target="id.bookId")
    @Mapping(target="user", source="userId", qualifiedByName="userIdToUser")
    BookRatings toEntity(BookRatingsDTO dto);

    @Mapping(source="id.bookId", target="bookId")
    @Mapping(source="id.userId", target="userId")
    BookRatingsDTO toDTO(BookRatings entity);
}

@Component
@AllArgsConstructor
class BookRatingsDTOMapperHelpers {

    private final UserRepository userRepository;

    @Named("userIdToUser")
    public @Nullable User userIdToUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
            new RuntimeException(
                "User with Id %s does not exist".formatted(userId)
            )
        );
    }
}