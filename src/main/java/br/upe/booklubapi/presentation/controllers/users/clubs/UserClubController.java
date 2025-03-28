package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Tag(
    name="User's Book Clubs",
    description="Endpoints to get clubs of a specific user"
)
public interface UserClubController {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    ResponseEntity<PagedModel<ClubDTO>> findAllByOwnerId(
        Optional<String> name,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate,
        Optional<Boolean> isPrivate,
        UUID ownerId,
        Pageable pageable
    );

}
