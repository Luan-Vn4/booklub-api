package br.upe.booklubapi.presentation.controllers.clubs.requests;

import br.upe.booklubapi.app.clubs.services.ClubMembersService;
import br.upe.booklubapi.domain.clubs.entities.ClubPendingEntry;
import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clubs/{club-id}/requests")
@AllArgsConstructor
public class ClubRequestsController {

    private final ClubMembersService clubMembersService;

    @GetMapping
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
