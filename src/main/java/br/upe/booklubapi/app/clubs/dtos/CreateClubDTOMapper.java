package br.upe.booklubapi.app.clubs.dtos;

import br.upe.booklubapi.app.clubs.services.ClubMediaStorageService;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.UUID;

@Mapper(
    unmappedTargetPolicy=ReportingPolicy.IGNORE,
    componentModel=MappingConstants.ComponentModel.SPRING,
    uses = {CreateClubDTOMapperHelpers.class}
)
public interface CreateClubDTOMapper {

    @Mapping(target="owner", source="ownerId", qualifiedByName="ownerIdToOwner")
    @Mapping(
        target="creationDate",
        expression="java(createClubDTOMapperHelpers.createDate())"
    )
    @Mapping(
        target="imageUrl",
        expression=("""
            java(createClubDTOMapperHelpers.imageToImageUrl(
                createClubDTO.image(), createClubDTO.name()
            ))
        """)
    )
    Club toEntity(CreateClubDTO createClubDTO);

}

@Component
@AllArgsConstructor
class CreateClubDTOMapperHelpers {

    private final UserRepository userRepository;

    private final ClubMediaStorageService clubMediaStorageService;

    @Named("ownerIdToOwner")
    public @Nullable User ownerIdToOwner(UUID ownerId) {
        return userRepository.findById(ownerId).orElseThrow(() ->
            new RuntimeException(
                "User with Id %s does not exist".formatted(ownerId)
            )
        );
    }

    public LocalDate createDate() {
        return LocalDate.now();
    }

    public String imageToImageUrl(MultipartFile image, String name) {
        return clubMediaStorageService.saveClubPicture(image, name);
    }

}