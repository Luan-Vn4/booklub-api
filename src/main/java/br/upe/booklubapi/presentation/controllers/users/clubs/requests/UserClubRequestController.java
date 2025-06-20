package br.upe.booklubapi.presentation.controllers.users.clubs.requests;

import br.upe.booklubapi.app.clubs.dtos.ClubPendingEntryDTO;
import br.upe.booklubapi.app.clubs.services.ClubMembersService;
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
@RequestMapping("api/v1/users/{user-id}/requests")
@AllArgsConstructor
@Tag(
    name="Membership - Users - Requests",
    description="Operations for managing user's club requests"
)
public class UserClubRequestController {

    private final ClubMembersService clubMembersService;

    @GetMapping("clubs/{club-id}/requests")
    @Operation(
        summary = "Get Request sent by user to this club"
    )
    public ResponseEntity<ClubPendingEntryDTO> getRequest(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("club-id")
        UUID clubId
    ) {
        final var result = clubMembersService.getUserPendingEntry(
            userId,
            clubId
        );

        return result
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/requests")
    @Operation(
        summary="Search all requests sent by the user"
    )
    public ResponseEntity<PagedModel<ClubPendingEntryDTO>> findAllUserRequests(
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

    @PostMapping("/requests/{club-id}")
    @Operation(
        summary="Send a request to join a club"
    )
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

    @DeleteMapping("/requests/{club-id}/cancel")
    @Operation(
        summary="Cancel a request to join a club"
    )
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
