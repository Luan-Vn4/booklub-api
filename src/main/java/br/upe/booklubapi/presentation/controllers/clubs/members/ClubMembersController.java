package br.upe.booklubapi.presentation.controllers.clubs.members;

import br.upe.booklubapi.app.clubs.services.ClubMembersService;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clubs/{club-id}/members")
@AllArgsConstructor
public class ClubMembersController {

    private final ClubMembersService clubMembersService;

    @GetMapping
    public ResponseEntity<PagedModel<UserDTO>> findAllMembers(
        @PathVariable("club-id")
        UUID clubId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            clubMembersService.findAllClubMembers(clubId, pageable)
        );
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<String> removeMember(
        @PathVariable("club-id")
        UUID clubId,
        @PathVariable("user-id")
        UUID userId
    ) {
        clubMembersService.removeClubMember(userId, clubId);
        return ResponseEntity.ok(
            "Member with id \"%s\" removed from club with id \"%s\""
                .formatted(userId, clubId)
        );
    }

}
