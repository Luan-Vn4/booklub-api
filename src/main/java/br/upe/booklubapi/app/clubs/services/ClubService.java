package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import java.time.LocalDate;
import java.util.UUID;

public interface ClubService {

    ClubDTO create(CreateClubDTO dto);

    ClubDTO update(UpdateClubDTO dto, UUID id);

    void delete(UUID id);

    ClubDTO findById(UUID id);

    PagedModel<ClubDTO> findAll(Pageable pageable);

    PagedModel<ClubDTO> searchByName(String name, Pageable pageable);

    PagedModel<ClubDTO> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    );

    PagedModel<ClubDTO> findByOwnerId(UUID ownerId, Pageable pageable);

    PagedModel<ClubDTO> findAllPublic(Pageable pageable);

}
