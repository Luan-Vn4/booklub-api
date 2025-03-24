package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.UUID;

public interface ClubService {

    ClubDTO create(CreateClubDTO dto);

    ClubDTO update(UpdateClubDTO dto);

    ClubDTO delete(UUID id);

    ClubDTO findById(UUID id);

    Page<ClubDTO> findAll(Pageable pageable);

    Page<ClubDTO> searchByName(String name, Pageable pageable);

    Page<ClubDTO> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    );

}
