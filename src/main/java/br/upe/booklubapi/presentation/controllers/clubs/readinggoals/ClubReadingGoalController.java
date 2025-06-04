package br.upe.booklubapi.presentation.controllers.clubs.readinggoals;

import br.upe.booklubapi.app.readinggoals.dtos.CreateReadingGoalDTO;
import br.upe.booklubapi.app.readinggoals.dtos.ReadingGoalDTO;
import br.upe.booklubapi.app.readinggoals.dtos.ReadingGoalQueryDTO;
import br.upe.booklubapi.app.readinggoals.services.ReadingGoalService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clubs/{clubId}/reading-goals")
@Tag(name=ApiTag.READING_GOAL)
public class ClubReadingGoalController {

    private final ReadingGoalService readingGoalService;

    @PostMapping
    @Operation(summary="Adds reading goal to club")
    public ResponseEntity<ReadingGoalDTO> addReadingGoal(
        @PathVariable(name="clubId")
        UUID clubId,
        @RequestBody
        CreateReadingGoalDTO dto
    ) {
        return ResponseEntity.ok(readingGoalService.addReadingGoal(
            clubId,
            dto
        ));
    }

    @GetMapping
    @Operation(summary="Get reading goals of a club")
    public ResponseEntity<PagedModel<ReadingGoalDTO>> getReadingGoals(
        @PathVariable(name="clubId")
        UUID clubId,
        @RequestParam
        Optional<LocalDate> startDate,
        @RequestParam
        Optional<LocalDate> endDate,
        Pageable pageable
    ) {
        final ReadingGoalQueryDTO dto = new ReadingGoalQueryDTO(
            startDate,
            endDate
        );
        return ResponseEntity.ok(readingGoalService.getReadingGoals(
            clubId,
            pageable,
            dto
        ));
    }

    @GetMapping("/current")
    @Operation(summary="Get the current club's reading goal")
    public ResponseEntity<ReadingGoalDTO> getClubCurrentReadingGoal(
        @PathVariable(name="clubId")
        UUID clubId
    ) {
        return ResponseEntity.ok(readingGoalService.getClubCurrentReadingGoal(
            clubId
        ));
    }

}
