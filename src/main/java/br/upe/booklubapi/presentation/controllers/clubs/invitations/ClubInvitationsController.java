package br.upe.booklubapi.presentation.controllers.clubs.invitations;

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
@RequestMapping("/api/v1/clubs/{club-id}/invitations")
@AllArgsConstructor
public class ClubInvitationsController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    public ResponseEntity<PagedModel<ClubPendingEntry>> findAllClubInvitations(
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

}
