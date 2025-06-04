package br.upe.booklubapi.app.meetings.dtos;

import br.upe.booklubapi.domain.meetings.entities.Meeting;
import br.upe.booklubapi.utils.models.SimpleCoordinate;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    componentModel=MappingConstants.ComponentModel.SPRING,
    uses=UpdateMeetingDTOMapperHelpers.class
)
public interface UpdateMeetingDTOMapper {

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(
        source="latlng",
        target="latlng",
        qualifiedByName="simpleCoordinateToPoint"
    )
    Meeting partialUpdate(
        UpdateMeetingDTO dto,
        @MappingTarget Meeting meeting
    );

}

@Component
@AllArgsConstructor
class UpdateMeetingDTOMapperHelpers {

    private final GeometryFactory geometryFactory;

    @Named("simpleCoordinateToPoint")
    public Point simpleCoordinateToPoint(SimpleCoordinate simpleCoordinate) {
        Coordinate coordinate = new Coordinate(
            simpleCoordinate.longitude(),
            simpleCoordinate.latitude()
        );
        return geometryFactory.createPoint(coordinate);
    }

}