package br.upe.booklubapi.presentation.controllers.readinggoals.meetings;

import br.upe.booklubapi.app.meetings.dtos.MeetingDTO;
import br.upe.booklubapi.app.meetings.services.MeetingService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/reading-goals/{readingGoalId}/meeting")
@AllArgsConstructor
@Tag(name= ApiTag.MEETINGS)
public class ReadingGoalMeetingController {

    private final MeetingService meetingService;

    @GetMapping
    @Operation(summary="Get the meeting of a reading goal")
    public ResponseEntity<MeetingDTO> getMeeting(
        @PathVariable UUID readingGoalId
    ) {
        return ResponseEntity.ok(
            meetingService.getReadingGoalMeeting(readingGoalId)
        );
    }

}
