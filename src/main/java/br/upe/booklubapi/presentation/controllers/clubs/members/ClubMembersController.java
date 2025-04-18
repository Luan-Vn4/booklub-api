package br.upe.booklubapi.presentation.controllers.clubs.members;

import br.upe.booklubapi.app.clubs.services.ClubService;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clubs/{id}")
@AllArgsConstructor
public class ClubMembersController {

    private final ClubService clubService;

    @GetMapping("/members")
    public ResponseEntity<PagedModel<UserDTO>> findAllMembers(
        @PathVariable("id")
        UUID id,
        Pageable pageable
    ) {
        return ResponseEntity.ok(clubService.findAllMembers(id, pageable));
    }

}
