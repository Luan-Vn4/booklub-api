package br.upe.booklubapi.presentation.controllers.clubs;

import br.upe.booklubapi.app.clubs.dtos.ClubDTO;
import br.upe.booklubapi.app.clubs.dtos.CreateClubDTO;
import br.upe.booklubapi.app.clubs.dtos.UpdateClubDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Tag(
    name="Book Clubs",
    description="Endpoints to get book clubs"
)
public interface ClubController {

    ResponseEntity<ClubDTO> create(CreateClubDTO dto);

    ResponseEntity<ClubDTO> update(UpdateClubDTO dto, UUID id);

    ResponseEntity<?> delete(UUID id);

    ResponseEntity<ClubDTO> findById(UUID id);

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    ResponseEntity<PagedModel<ClubDTO>> search(
        Optional<String> name,
        Optional<LocalDate> startDate,
        Optional<LocalDate> endDate,
        Optional<Boolean> isPrivate,
        Pageable pageable
    );

}
