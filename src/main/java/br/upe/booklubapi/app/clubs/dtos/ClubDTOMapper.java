package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses={ClubDTOMapperHelpers.class}
)
public interface ClubDTOMapper {

    @Mapping(target="owner", source="ownerId", qualifiedByName="ownerIdToOwner")
    Club toEntity(ClubDTO clubDTO);

    @Mapping(target="ownerId", source="owner", qualifiedByName="ownerToOwnerId")
    ClubDTO toDto(Club club);

    @BeanMapping(
        nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target="owner", source="ownerId", qualifiedByName="ownerIdToOwner")
    Club partialUpdate(ClubDTO clubDTO, @MappingTarget Club club);

}

@Component
@AllArgsConstructor
class ClubDTOMapperHelpers {

    UserRepository userRepository;

    @Named("ownerIdToOwner")
    public User ownerIdToOwner(UUID ownerId) {
        return userRepository.findById(ownerId).orElseThrow(() ->
            new RuntimeException(
                "Owner with id %s not found".formatted(ownerId)
            )
        );
    }

    @Named("ownerToOwnerId")
    public UUID ownerToOwnerId(User owner) {
        return owner.getId();
    }

}
