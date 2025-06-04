package br.upe.booklubapi.infra.meetings.repositories;

import br.upe.booklubapi.domain.meetings.entities.Meeting;
import br.upe.booklubapi.domain.meetings.repositories.MeetingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface JpaMeetingsRepository
        extends MeetingRepository,
                JpaRepository<Meeting, UUID>,
                QuerydslPredicateExecutor<Meeting> {

    @Override
    @Query("""
        SELECT rg.meeting FROM ReadingGoal rg
            WHERE rg.club.id = :clubId AND CURRENT TIMESTAMP
                BETWEEN rg.startDate AND rg.endDate
    """)
    Optional<Meeting> findClubNextMeeting(UUID clubId);

    @Override
    Optional<Meeting> findMeetingByReadingGoalId(UUID readingGoalId);

    @Override
    @Query("""
        SELECT m FROM Club c
            JOIN Meeting m
                ON c.id=:clubId AND m.readingGoal.club.id=:clubId
            ORDER BY m.readingGoal.endDate DESC
    """)
    Page<Meeting> findClubMeetings(UUID clubId, Pageable pageable);

}
