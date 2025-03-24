package br.upe.booklubapi.presentation.controllers.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import br.upe.booklubapi.app.clubs.services.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clubs")
@AllArgsConstructor
public class ClubControllerImpl implements ClubController {

    private ClubService clubService;

    @Override
    @PostMapping("/")
    public ResponseEntity<ClubDTO> create(CreateClubDTO dto) {
        // TODO
        return null;
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<ClubDTO> update(UpdateClubDTO dto) {
        // TODO
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ClubDTO> delete(UUID id) {
        // TODO
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> findById(UUID id) {
        // TODO
        return null;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<ClubDTO>> findAll(Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    @GetMapping(params={"name"})
    public ResponseEntity<Page<ClubDTO>> searchByName(
        @RequestParam("name")
        String name,
        Pageable pageable
    ) {
        // TODO
        return null;
    }

    @Override
    @GetMapping(params={"start-date", "end-date"})
    public ResponseEntity<Page<ClubDTO>> searchByDate(
        @RequestParam("start-date")
        LocalDate start,
        @RequestParam("end-date")
        LocalDate end,
        Pageable pageable
    ) {
        // TODO
        return null;
    }

}
