package br.upe.booklubapi.app.books.dtos;

import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.UUID;

import br.upe.booklubapi.domain.books.entities.BookUserProgress;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={BookUserProgressDTOMapperHelpers.class}
)
public interface BookUserProgressDTOMapper {

    @Mapping(source="bookId", target="id.bookId")
    @Mapping(target="user", source="userId", qualifiedByName="userIdToUser")
    BookUserProgress toEntity(BookUserProgressDTO dto);

    @Mapping(source="id.bookId", target="bookId")
    @Mapping(source="id.userId", target="userId")
    BookUserProgressDTO toDTO(BookUserProgress entity);
}

@Component
@AllArgsConstructor
class BookUserProgressDTOMapperHelpers {

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