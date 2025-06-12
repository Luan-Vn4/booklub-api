package br.upe.booklubapi.app.books.dtos;

import br.upe.booklubapi.domain.books.entities.BookUser;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={BookUserDTOMapperHelpers.class}
)
public interface BookUserDTOMapper {

    @Mapping(source="bookId", target="id.bookId")
    @Mapping(source="userId", target="id.userId")
    @Mapping(target="user", source="userId", qualifiedByName="userIdToUser")
    BookUser toEntity(BookUserDTO dto);

    @Mapping(source="id.bookId", target="bookId")
    @Mapping(source="id.userId", target="userId")
    BookUserDTO toDTO(BookUser entity);
}

@Component
@AllArgsConstructor
class BookUserDTOMapperHelpers {

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