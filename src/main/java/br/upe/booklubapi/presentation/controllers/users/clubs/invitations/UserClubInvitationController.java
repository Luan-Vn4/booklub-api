package br.upe.booklubapi.presentation.controllers.users.clubs.invitations;

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
@RequestMapping("api/v1/users/{user-id}/invitations")
@AllArgsConstructor
public class UserClubInvitationController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    public ResponseEntity<PagedModel<ClubPendingEntry>> findAllUserInvitations(
        @PathVariable("user-id")
        UUID userId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllPendingEntriesByUserId(
                userId,
                pageable,
                EntryType.INVITATION
            )
        );
    }

    @PostMapping("/{club-id}/accept")
    public ResponseEntity<String> acceptInvitation(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("club-id")
        UUID clubId
    ) {
        clubMembersService.acceptInvitation(clubId, userId);
        return ResponseEntity.ok((
            "User with id \"%s\" accepted invitation from club with id \"%s\"."
        ).formatted(userId, clubId));
    }

    @DeleteMapping("/{club-id}/deny")
    public ResponseEntity<String> denyInvitation(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("club-id")
        UUID clubId
    ) {
        clubMembersService.declineInvitation(clubId, userId);
        return ResponseEntity.ok((
            "User with id \"%s\" denied invitation from club with id \"%s\"."
        ).formatted(userId, clubId));
    }

}