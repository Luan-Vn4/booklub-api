package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.*;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.exceptions.ClubNotFoundException;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final CreateClubDTOMapper createClubDTOMapper;

    private final ClubDTOMapper clubDTOMapper;

    private final UpdateClubDTOMapper updateClubDTOMapper;

    private final ClubRepository clubRepository;

    @Override
    @Transactional
    public ClubDTO create(CreateClubDTO dto) {
        final Club club = createClubDTOMapper.toEntity(dto);
        return clubDTOMapper.toDto(clubRepository.save(club));
    }

    @Override
    @Transactional
    public ClubDTO update(UpdateClubDTO dto, UUID id) {
        Club current = clubRepository.findById(id).orElseThrow(
            () -> new ClubNotFoundException(id)
        );
        return clubDTOMapper.toDto(
            updateClubDTOMapper.partialUpdate(dto, current)
        );
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        boolean exists = clubRepository.existsById(id);
        if (!exists) throw new ClubNotFoundException(id);
        clubRepository.deleteById(id);
    }

    @Override
    public ClubDTO findById(UUID id) {
        Club club = clubRepository.findById(id).orElseThrow(
            () -> new ClubNotFoundException(id)
        );
        return clubDTOMapper.toDto(club);
    }

    @Override
    public PagedModel<ClubDTO> findAll(Pageable pageable) {
        return new PagedModel<>(
            clubRepository.findAll(pageable)
                .map(clubDTOMapper::toDto)
        );
    }

    @Override
    public PagedModel<ClubDTO> searchByName(String name, Pageable pageable) {
        return new PagedModel<>(
            clubRepository.searchByName(name, pageable)
                .map(clubDTOMapper::toDto)
        );
    }

    @Override
    public PagedModel<ClubDTO> searchByDate(
        LocalDate start,
        LocalDate end,
        Pageable pageable
    ) {
        return new PagedModel<>(
            clubRepository.searchByCreationDateBetween(start, end, pageable)
                .map(clubDTOMapper::toDto)
        );
    }

}
