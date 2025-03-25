package br.upe.booklubapi.presentation.controllers.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.UUID;

public interface ClubController {

    ResponseEntity<ClubDTO> create(CreateClubDTO dto);

    ResponseEntity<ClubDTO> update(UpdateClubDTO dto, UUID id);

    ResponseEntity<?> delete(UUID id);

    ResponseEntity<ClubDTO> findById(UUID id);

    ResponseEntity<PagedModel<ClubDTO>> findAll(Pageable pageable);

    ResponseEntity<PagedModel<ClubDTO>> searchByName(String name, Pageable pageable);

    ResponseEntity<PagedModel<ClubDTO>> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    );

    ResponseEntity<PagedModel<ClubDTO>> findAllPublic(Pageable pageable);

}
