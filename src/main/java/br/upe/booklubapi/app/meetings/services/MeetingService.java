package br.upe.booklubapi.app.meetings.services;

import br.upe.booklubapi.app.meetings.dtos.CreateMeetingDTO;
import br.upe.booklubapi.app.meetings.dtos.MeetingDTO;
import br.upe.booklubapi.app.meetings.dtos.UpdateMeetingDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import java.util.UUID;

public interface MeetingService {

    MeetingDTO defineMeeting(CreateMeetingDTO dto);

    void cancelMeeting(UUID id);

    MeetingDTO updateMeeting(UUID meetingId, UpdateMeetingDTO dto);

    MeetingDTO getMeeting(UUID id);

    MeetingDTO getReadingGoalMeeting(UUID readingGoalId);

    MeetingDTO getClubNextMeeting(UUID clubId);

    PagedModel<MeetingDTO> getClubMeetings(UUID clubId, Pageable pageable);

}
