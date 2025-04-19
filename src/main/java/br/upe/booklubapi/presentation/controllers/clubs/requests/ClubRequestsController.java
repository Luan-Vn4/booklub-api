package br.upe.booklubapi.presentation.controllers.clubs.requests;

import br.upe.booklubapi.app.clubs.services.ClubMembersService;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clubs/{club-id}/requests")
@AllArgsConstructor
@Tag(
    name="Membership - Clubs - Requests",
    description="Operations for managing club membership requests"
)
public class ClubRequestsController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    @Operation(
        summary="Search all requests to join club"
    )
    public ResponseEntity<PagedModel<ClubPendingEntry>> findAllClubRequests(
        @PathVariable("club-id")
        UUID clubId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllPendingEntriesByClubId(
                clubId,
                pageable,
                EntryType.REQUEST
            )
        );
    }

    @PostMapping("/{user-id}/accept")
    @Operation(
        summary="Accept a request to join a club"
    )
    public ResponseEntity<String> acceptRequest(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.acceptRequest(clubId, userId);
        return ResponseEntity.ok((
            "Request from user with id \"%s\" to join club with id \"%s\" " +
            "was accepted"
        ).formatted(userId, clubId));
    }

    @PostMapping("/{user-id}/deny")
    @Operation(
        summary="Deny a request to join a club"
    )
    public ResponseEntity<String> denyRequest(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.declineRequest(clubId, userId);
        return ResponseEntity.ok((
            "Request from user with id \"%s\" to join club with id \"%s\" " +
                "was denied"
        ).formatted(userId, clubId));
    }

}
