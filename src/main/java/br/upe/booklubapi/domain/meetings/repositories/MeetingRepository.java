package br.upe.booklubapi.domain.meetings.repositories;

import br.upe.booklubapi.domain.core.repositories.CrudRepository;
import br.upe.booklubapi.domain.meetings.entities.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MeetingRepository
    extends CrudRepository<Meeting, UUID> {

    Optional<Meeting> findClubNextMeeting(UUID clubId);

    Optional<Meeting> findMeetingByReadingGoalId(UUID readingGoalId);

    Page<Meeting> findClubMeetings(UUID clubId, Pageable pageable);

}
