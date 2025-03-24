package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ClubServiceImpl implements ClubService {

    @Override
    @Transactional
    public ClubDTO create(CreateClubDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public ClubDTO update(UpdateClubDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public ClubDTO delete(UUID id) {
        return null;
    }

    @Override
    public ClubDTO findById(UUID id) {
        return null;
    }

    @Override
    public Page<ClubDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ClubDTO> searchByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ClubDTO> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    ) {
        return null;
    }

}
