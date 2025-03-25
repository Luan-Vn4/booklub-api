package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(
    name="User's Book Clubs",
    description="Endpoints to get clubs of a specific user"
)
public interface UserClubController {

    ResponseEntity<PagedModel<ClubDTO>> findByOwnerId(
        UUID ownerId,
        Pageable pageable
    );

}
