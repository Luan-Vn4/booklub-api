package br.upe.booklubapi.presentation.controllers.readinggoals;

import br.upe.booklubapi.app.readinggoals.dtos.ReadingGoalDTO;
import br.upe.booklubapi.app.readinggoals.dtos.UpdateReadingGoalDTO;
import br.upe.booklubapi.app.readinggoals.services.ReadingGoalService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/reading-goals")
@AllArgsConstructor
@Tag(name=ApiTag.READING_GOAL)
public class ReadingGoalController {

    private final ReadingGoalService readingGoalService;

    @GetMapping("/{readingGoalId}")
    @Operation(summary="Get specific reading goal")
    public ResponseEntity<ReadingGoalDTO> getReadingGoal(
        @PathVariable(name="readingGoalId")
        UUID readingGoalId
    ) {
        return ResponseEntity.ok(readingGoalService.getReadingGoal(
            readingGoalId
        ));
    }

    @PutMapping("/{readingGoalId}")
    @Operation(summary="Update reading goal")
    public ResponseEntity<ReadingGoalDTO> updateReadingGoal(
        @PathVariable(name="readingGoalId")
        UUID readingGoalId,
        @RequestBody
        UpdateReadingGoalDTO dto
    ) {
        return ResponseEntity.ok(readingGoalService.updateReadingGoal(
            readingGoalId,
            dto
        ));
    }

    @DeleteMapping("/{readingGoalId}")
    @Operation(summary="Delete reading goal")
    public ResponseEntity<?> deleteReadingGoal(
        @PathVariable(name="readingGoalId")
        UUID readingGoalId
    ) {
        readingGoalService.deleteReadingGoal(readingGoalId);
        return ResponseEntity.ok().build();
    }

}
