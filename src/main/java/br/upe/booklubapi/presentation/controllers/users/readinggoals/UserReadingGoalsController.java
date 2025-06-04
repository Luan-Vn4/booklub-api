package br.upe.booklubapi.presentation.controllers.users.readinggoals;

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
@RequestMapping("api/v1/user/{userId}/reading-goals")
@AllArgsConstructor
@Tag(name=ApiTag.READING_GOAL)
public class UserReadingGoalsController {

    private final ReadingGoalService readingGoalService;

    @GetMapping
    @Operation(
        summary="Get the reading goals of the clubs that the user participates"
    )
    public ResponseEntity<PagedModel<ReadingGoalDTO>> getReadingGoals(
        @PathVariable
        UUID userId,
        @RequestParam
        Optional<LocalDate> startDate,
        @RequestParam
        Optional<LocalDate> endDate,
        Pageable pageable
    ) {
        return ResponseEntity.ok(readingGoalService.getUserReadingGoals(
            userId,
            pageable,
            new ReadingGoalQueryDTO(
                startDate,
                endDate
            )
        ));
    }

}
