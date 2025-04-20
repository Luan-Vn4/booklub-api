package br.upe.booklubapi.presentation.controllers.clubs.invitations;

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
@RequestMapping("/api/v1/clubs/{club-id}/invitations")
@AllArgsConstructor
@Tag(
    name="Membership - Clubs - Invitations",
    description="Operations for managing club membership invitations"
)
public class ClubInvitationsController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    @Operation(
        summary="Search all club invitations"
    )
    public ResponseEntity<PagedModel<ClubPendingEntryDTO>> findAllClubInvitations(
        @PathVariable("club-id")
        UUID clubId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllPendingEntriesByClubId(
                clubId,
                pageable,
                EntryType.INVITATION
            )
        );
    }

    @PostMapping("/{user-id}")
    @Operation(
        summary="Invite a user to join a club"
    )
    public ResponseEntity<String> inviteUser(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.sendInvitation(clubId, userId);
        return ResponseEntity.ok(
            "User with id \"%s\" invited to club with id \"%s\""
                .formatted(userId, clubId)
        );
    }

    @DeleteMapping("/{user-id}/cancel")
    @Operation(
        summary="Cancel a user's invitation to join a club"
    )
    public ResponseEntity<String> cancelInvitation(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.cancelInvitation(clubId, userId);
        return ResponseEntity.ok((
            "Cancelled invitation to user with id \"%s\" to " +
            "join club with id \"%s\""
        ).formatted(userId, clubId));
    }

}