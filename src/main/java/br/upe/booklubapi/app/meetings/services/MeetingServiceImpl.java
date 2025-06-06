package br.upe.booklubapi.app.meetings.services;

import br.upe.booklubapi.app.meetings.dtos.*;
import br.upe.booklubapi.app.meetings.exceptions.MeetingAlreadyDefinedException;
import br.upe.booklubapi.app.meetings.exceptions.MeetingNotDefinedException;
import br.upe.booklubapi.app.meetings.exceptions.MeetingNotFoundException;
import br.upe.booklubapi.app.meetings.exceptions.NoNextMeetingException;
import br.upe.booklubapi.domain.activities.entities.clubactivities.MeetingDefinedActivity;
import br.upe.booklubapi.domain.activities.repositories.ActivityRepository;
import br.upe.booklubapi.domain.meetings.entities.Meeting;
import br.upe.booklubapi.domain.meetings.repositories.MeetingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class MeetingServiceImpl implements MeetingService {

    private final MeetingDTOMapper meetingDTOMapper;

    private final CreateMeetingDTOMapper createMeetingDTOMapper;

    private final UpdateMeetingDTOMapper updateMeetingDTOMapper;

    private final MeetingRepository meetingRepository;

    private final ActivityRepository activityRepository;

    private Meeting getMeeeting(UUID meetingId) {
        return meetingRepository
            .findById(meetingId)
            .orElseThrow(() -> new MeetingNotFoundException(meetingId));
    }

    @Override
    public MeetingDTO defineMeeting(CreateMeetingDTO dto) {
        final Optional<Meeting> maybeMeeting = meetingRepository
            .findMeetingByReadingGoalId(dto.readingGoalId());

        if (maybeMeeting.isPresent()) {
            throw new MeetingAlreadyDefinedException(dto.readingGoalId());
        }

        final Meeting meeting = createMeetingDTOMapper.toEntity(dto);
        final Meeting createdMeeting = meetingRepository.save(meeting);

        publishMeetingDefinedActivity(createdMeeting);

        return meetingDTOMapper.toDto(createdMeeting);
    }

    private void publishMeetingDefinedActivity(Meeting meeting) {
        final var activity = MeetingDefinedActivity.builder()
            .club(meeting.getReadingGoal().getClub())
            .meeting(meeting)
            .build();

        activityRepository.save(activity);
    }

    @Override
    public void cancelMeeting(UUID id) {
        if (!meetingRepository.existsById(id)) {
            throw new MeetingNotFoundException(id);
        }
        meetingRepository.deleteById(id);
    }

    @Override
    public MeetingDTO updateMeeting(UUID meetingId, UpdateMeetingDTO dto) {
        final Meeting meeting = getMeeeting(meetingId);

        final Meeting updated = updateMeetingDTOMapper.partialUpdate(
            dto,
            meeting
        );

        return meetingDTOMapper.toDto(meetingRepository.save(updated));
    }

    @Override
    public MeetingDTO getMeeting(UUID uuid) {
        return meetingDTOMapper.toDto(getMeeeting(uuid));
    }

    @Override
    public MeetingDTO getReadingGoalMeeting(UUID readingGoalId) {
        final Meeting readingGoalMeeting = meetingRepository
            .findMeetingByReadingGoalId(readingGoalId)
            .orElseThrow(() -> new MeetingNotDefinedException(readingGoalId));

        return meetingDTOMapper.toDto(readingGoalMeeting);
    }

    @Override
    public MeetingDTO getClubNextMeeting(UUID clubId) {
        final Meeting nextMeeting = meetingRepository
            .findClubNextMeeting(clubId)
            .orElseThrow(() -> new NoNextMeetingException(clubId));

        return meetingDTOMapper.toDto(nextMeeting);
    }

    @Override
    public PagedModel<MeetingDTO> getClubMeetings(UUID clubId, Pageable pageable) {
        return new PagedModel<>(meetingRepository
            .findClubMeetings(clubId, pageable)
            .map(meetingDTOMapper::toDto)
        );
    }

}
