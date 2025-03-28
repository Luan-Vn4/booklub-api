package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.QueryClubDTO;
import br.upe.booklubapi.app.clubs.services.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{ownerId}")
@AllArgsConstructor
public class UserClubControllerImpl implements UserClubController {

    private final ClubService clubService;

    @Override
    @GetMapping("/clubs")
    public ResponseEntity<PagedModel<ClubDTO>> findAllByOwnerId(
        @RequestParam
        Optional<String> name,
        @RequestParam
        Optional<LocalDate> startDate,
        @RequestParam
        Optional<LocalDate> endDate,
        @RequestParam
        Optional<Boolean> isPrivate,
        @PathVariable(name="ownerId")
        UUID ownerId,
        Pageable pageable
    ) {
        final var result = clubService.findAll(new QueryClubDTO(
            name,
            startDate,
            endDate,
            isPrivate,
            Optional.of(ownerId)
        ), pageable);
        return ResponseEntity.ok(result);
    }

}
