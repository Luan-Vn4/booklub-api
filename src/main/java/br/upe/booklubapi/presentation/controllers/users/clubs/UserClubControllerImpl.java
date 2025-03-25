package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.services.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{ownerId}")
@AllArgsConstructor
public class UserClubControllerImpl implements UserClubController {

    private final ClubService clubService;

    @Override
    @GetMapping("/clubs")
    public ResponseEntity<PagedModel<ClubDTO>> findByOwnerId(
        @PathVariable(name="ownerId") UUID ownerId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(clubService.findByOwnerId(ownerId, pageable));
    }
}
