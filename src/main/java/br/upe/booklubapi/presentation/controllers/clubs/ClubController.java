package br.upe.booklubapi.presentation.controllers.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

public interface ClubController {

    ResponseEntity<ClubDTO> create(CreateClubDTO dto);

    ResponseEntity<ClubDTO> update(UpdateClubDTO dto);

    ResponseEntity<ClubDTO> delete(UUID id);

    ResponseEntity<ClubDTO> findById(UUID id);

    ResponseEntity<Page<ClubDTO>> findAll(Pageable pageable);

    ResponseEntity<Page<ClubDTO>> searchByName(String name, Pageable pageable);

    ResponseEntity<Page<ClubDTO>> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    );

}
