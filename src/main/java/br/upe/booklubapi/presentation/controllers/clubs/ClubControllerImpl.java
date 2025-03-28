package br.upe.booklubapi.presentation.controllers.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.QueryClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import br.upe.booklubapi.app.clubs.services.ClubService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clubs")
@AllArgsConstructor
public class ClubControllerImpl implements ClubController {

    private ClubService clubService;

    @Override
    @PostMapping
    public ResponseEntity<ClubDTO> create(
        @Valid
        @RequestBody
        CreateClubDTO dto
    ) {
        return ResponseEntity.ok(clubService.create(dto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ClubDTO> update(
        @Valid
        @RequestBody
        UpdateClubDTO dto,
        @PathVariable(name="id")
        UUID id
    ) {
        return ResponseEntity.ok(clubService.update(dto, id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        clubService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(clubService.findById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<ClubDTO>> search(
        @RequestParam
        Optional<String> name,
        @RequestParam
        Optional<LocalDate> startDate,
        @RequestParam
        Optional<LocalDate> endDate,
        @RequestParam
        Optional<Boolean> isPrivate,
        Pageable pageable
    ) {
        final var result = clubService.findAll(new QueryClubDTO(
            name,
            startDate,
            endDate,
            isPrivate,
            Optional.empty()
        ), pageable);
        return ResponseEntity.ok(result);
    }

}
