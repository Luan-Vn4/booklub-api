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
    ReadingGoal toEntity(CreateReadingGoalDTO dto, @Context Club club);

    @AfterMapping
    default void afterMapping(
        CreateReadingGoalDTO dto,
        @MappingTarget ReadingGoal readingGoal,
        @Context Club club
    ) {
        readingGoal.setClub(club);
    }


}

@Component
@AllArgsConstructor
class CreateReadingGoalDTOMapperHelpers {

    private final ClubRepository clubRepository;

    @Named("clubIdToClub")
    public Club clubIdToClub(@Context UUID clubId) {
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new ClubNotFoundException(clubId));
    }

}
