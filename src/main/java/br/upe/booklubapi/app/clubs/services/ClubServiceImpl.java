package br.upe.booklubapi.app.clubs.services;

import br.upe.booklubapi.app.clubs.dtos.*;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.entities.QClub;
import br.upe.booklubapi.domain.clubs.exceptions.ClubNotFoundException;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final CreateClubDTOMapper createClubDTOMapper;

    private final ClubDTOMapper clubDTOMapper;

    private final UserDTOMapper userDTOMapper;

    private final UpdateClubDTOMapper updateClubDTOMapper;  

    private final ClubRepository clubRepository;

    private final QClub club = QClub.club;

    @Override
    @Transactional
    public ClubDTO create(CreateClubDTO dto) {
        final Club club = createClubDTOMapper.toEntity(dto);

        System.out.println(club);

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
    public PagedModel<ClubDTO> findAll(
        QueryClubDTO queryDTO,
        Pageable pageable
    ) {
        return new PagedModel<>(
            clubRepository.findAll(queryDTO.getQuery(club), pageable)
                .map(clubDTOMapper::toDto)
        );
    }

    @Override
    public PagedModel<UserDTO> findAllMembers(UUID clubId, Pageable pageable) {
        return new PagedModel<>(
            clubRepository.findAllMembers(clubId, Expressions.TRUE, pageable)
                .map(userDTOMapper::toDto)
        );
    }

}
