package br.upe.booklubapi.app.readinggoals.dtos;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.exceptions.ClubNotFoundException;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {CreateReadingGoalDTOMapperHelpers.class}
)
public interface CreateReadingGoalDTOMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "club", source = "clubId", qualifiedByName = "clubIdToClub")
    ReadingGoal toEntity(CreateReadingGoalDTO dto);

}

@Component
@AllArgsConstructor
class CreateReadingGoalDTOMapperHelpers {

    private final ClubRepository clubRepository;

    @Named("clubIdToClub")
    public Club clubIdToClub(UUID clubId) {
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new ClubNotFoundException(clubId));
    }

}
