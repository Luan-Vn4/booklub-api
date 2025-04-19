package br.upe.booklubapi.presentation.controllers.users.clubs.requests;

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
@RequestMapping("api/v1/users/{user-id}/requests")
@AllArgsConstructor
public class UserClubRequestController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    public ResponseEntity<PagedModel<ClubPendingEntry>> findAllUserRequests(
        @PathVariable("user-id")
        UUID userId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllPendingEntriesByUserId(
                userId,
                pageable,
                EntryType.REQUEST
            )
        );
    }

    @PostMapping("/{club-id}")
    public ResponseEntity<String> sendRequest(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.sendRequest(clubId, userId);
        return ResponseEntity.ok(
            "User with id \"%s\" sent a request to join club with id \"%s\""
                .formatted(userId, clubId)
        );
    }

    @DeleteMapping("/{club-id}/cancel")
    public ResponseEntity<String> cancelRequest(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.cancelRequest(clubId, userId);
        return ResponseEntity.ok(
            "User with id \"%s\" canceled request to join club with id \"%s\""
                .formatted(userId, clubId)
        );
    }

}
