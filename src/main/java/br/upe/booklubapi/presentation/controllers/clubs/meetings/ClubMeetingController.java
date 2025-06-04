package br.upe.booklubapi.presentation.controllers.clubs.meetings;

import br.upe.booklubapi.app.meetings.dtos.MeetingDTO;
import br.upe.booklubapi.app.meetings.services.MeetingService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clubs/{clubId}/meetings")
@Tag(name= ApiTag.MEETINGS)
@AllArgsConstructor
public class ClubMeetingController {

    final MeetingService meetingService;

    @GetMapping("/next")
    @Operation(summary="Find the next meeting of a club")
    public ResponseEntity<MeetingDTO> findNextMeeting(
        @PathVariable(name="clubId")
        UUID clubId
    ) {
        return ResponseEntity.ok(meetingService.getClubNextMeeting(clubId));
    }

    @GetMapping
    @Operation(summary="Find meetings of a club")
    public ResponseEntity<PagedModel<MeetingDTO>> findMeetings(
        @PathVariable(name="clubId")
        UUID clubId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            meetingService.getClubMeetings(clubId, pageable)
        );
    }

}
