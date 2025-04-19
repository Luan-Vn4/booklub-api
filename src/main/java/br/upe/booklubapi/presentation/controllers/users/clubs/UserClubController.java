package br.upe.booklubapi.presentation.controllers.users.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.QueryClubDTO;
import br.upe.booklubapi.app.clubs.services.ClubMembersService;
import br.upe.booklubapi.app.clubs.services.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{user-id}/clubs")
@AllArgsConstructor
@Tag(name="Membership - Users", description="User's club membership operations")
public class UserClubController {

    private final ClubService clubService;

    private final ClubMembersService clubMembersService;

    @GetMapping("/owned")
    @Operation(
        summary="Search all clubs owned by the user"
    )
    public ResponseEntity<PagedModel<ClubDTO>> findAllOwnedClubs(
        @RequestParam
        Optional<String> name,
        @RequestParam
        Optional<LocalDate> startDate,
        @RequestParam
        Optional<LocalDate> endDate,
        @RequestParam
        Optional<Boolean> isPrivate,
        @PathVariable(name="user-id")
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

    @GetMapping("/participating")
    @Operation(
        summary="Search all clubs the user is a member"
    )
    public ResponseEntity<PagedModel<ClubDTO>> findAllClubsUserIsParticipating(
        @PathVariable("user-id")
        UUID userId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllUserClubs(userId, pageable)
        );
    }

    @DeleteMapping("/{club-id}")
    @Operation(
        summary="Leave the club"
    )
    public ResponseEntity<String> leaveClub(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("club-id")
        UUID clubId
    ) {
        clubMembersService.leaveClub(userId, clubId);
        return ResponseEntity.ok((
            "User with id \"%s\" left club with id \"%s\""
        ).formatted(userId, clubId));
    }

}
