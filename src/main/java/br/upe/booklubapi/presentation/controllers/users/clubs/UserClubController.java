package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserClubController {

    ResponseEntity<PagedModel<ClubDTO>> findByOwnerId(
        UUID ownerId,
        Pageable pageable
    );

}
