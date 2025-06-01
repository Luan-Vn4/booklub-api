package br.upe.booklubapi.app.meetings.dtos;

import br.upe.booklubapi.domain.meetings.entities.Meeting;
import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import br.upe.booklubapi.domain.readinggoals.exceptions.ReadingGoalNotFoundException;
import br.upe.booklubapi.domain.readinggoals.repositories.ReadingGoalRepository;
import br.upe.booklubapi.utils.models.SimpleCoordinate;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    componentModel=MappingConstants.ComponentModel.SPRING,
    uses=CreateMeetingDTOMapperHelpers.class
)
public interface CreateMeetingDTOMapper {

    @Mapping(
        source="readingGoalId",
        target="readingGoal",
        qualifiedByName="readingGoalIdToReadingGoal"
    )
    @Mapping(
        source="latlng",
        target="latlng",
        qualifiedByName="simpleCoordinateToPoint"
    )
    Meeting toEntity(CreateMeetingDTO dto);

}

@Component
@AllArgsConstructor
class CreateMeetingDTOMapperHelpers {

    private final ReadingGoalRepository readingGoalRepository;

    private final GeometryFactory geometryFactory;

    @Named("readingGoalIdToReadingGoal")
    public ReadingGoal readingGoalIdToReadingGoal(UUID readingGoalId) {
        return readingGoalRepository.findById(readingGoalId).orElseThrow(
            () -> new ReadingGoalNotFoundException(readingGoalId)
        );
    }

    @Named("simpleCoordinateToPoint")
    public Point simpleCoordinateToPoint(SimpleCoordinate simpleCoordinate) {
        Coordinate coordinate = new Coordinate(
            simpleCoordinate.longitude(),
            simpleCoordinate.latitude()
        );
        return geometryFactory.createPoint(coordinate);
    }

}