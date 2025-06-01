package br.upe.booklubapi.presentation.controllers.meetings;

import br.upe.booklubapi.app.meetings.dtos.CreateMeetingDTO;
import br.upe.booklubapi.app.meetings.dtos.MeetingDTO;
import br.upe.booklubapi.app.meetings.dtos.UpdateMeetingDTO;
import br.upe.booklubapi.app.meetings.services.MeetingService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/meetings")
@Tag(name= ApiTag.MEETINGS)
@AllArgsConstructor
public class MeetingsController {

    private final MeetingService meetingService;

    @GetMapping("/{meetingId}")
    @Operation(summary="Get specific meeting")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable UUID meetingId) {
        return ResponseEntity.ok(meetingService.getMeeting(meetingId));
    }

    @PostMapping
    @Operation(summary="Define a new meeting")
    public ResponseEntity<MeetingDTO> defineMeeting(
        @RequestBody
        CreateMeetingDTO dto
    ) {
        return ResponseEntity.ok(meetingService.defineMeeting(dto));
    }

    @PutMapping("/{meetingId}")
    @Operation(summary="Update a meeting")
    public ResponseEntity<MeetingDTO> updateMeeting(
        @PathVariable(name="meetingId")
        UUID meetingId,
        @RequestBody
        UpdateMeetingDTO dto
    ) {
        return ResponseEntity.ok(meetingService.updateMeeting(meetingId, dto));
    }

    @DeleteMapping("/{meetingId}")
    @Operation(summary="cancel a meeting")
    public ResponseEntity<?> cancelMeeting(
        @PathVariable(name="meetingId")
        UUID meetingId
    ) {
        meetingService.cancelMeeting(meetingId);
        return ResponseEntity.ok().build();
    }

}
