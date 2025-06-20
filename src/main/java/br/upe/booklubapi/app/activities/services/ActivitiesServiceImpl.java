package br.upe.booklubapi.app.activities.services;

import br.upe.booklubapi.app.activities.dtos.ActivityDTO;
import br.upe.booklubapi.app.activities.dtos.ClubActivityDTO;
import br.upe.booklubapi.app.activities.dtos.UserActivityDTO;
import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapperResolver;
import br.upe.booklubapi.domain.activities.entities.clubactivities.ClubActivity;
import br.upe.booklubapi.domain.activities.repositories.ActivityRepository;
import br.upe.booklubapi.domain.activities.repositories.ClubActivityRepository;
import br.upe.booklubapi.domain.activities.repositories.UserActivityRepository;
import br.upe.booklubapi.domain.clubs.entities.Club;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;
import br.upe.booklubapi.utils.UserUtils;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ActivitiesServiceImpl implements ActivitiesService {

    private final ActivityRepository activityRepository;

    private final ClubRepository clubRepository;

    private final ClubActivityRepository clubActivityRepository;

    private final UserActivityRepository userActivityRepository;

    private final ActivityDTOMapperResolver mapper;

    private final UserUtils userUtils;

    // TODO Melhorar essa gambiarra depois
    @Override
    public PagedModel<ActivityDTO> getActivitiesForUser(
        Pageable pageable
    ) {
        final UUID loggedUserId = userUtils.getLoggedUserId();

        final List<Club> clubsUserParticipates = clubRepository
            .findAllUserClubs(loggedUserId, Expressions.TRUE, Pageable.unpaged())
            .toList();

        final List<ClubActivity> clubActivities = clubsUserParticipates.stream()
            .map(club -> clubActivityRepository.findAllByClubId(club.getId(), Pageable.unpaged()))
            .flatMap(paged -> paged.toList().stream())
            .sorted(Comparator.comparing(ClubActivity::getCreatedAt))
            .toList();

        int total = clubActivities.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), total);

        if (start > total) {
            return new PagedModel<>(
                new PageImpl<>(
                    List.of(),
                    pageable,
                    total
                )
            );
        }

        List<ClubActivity> pagedList = clubActivities.subList(start, end);
        List<ActivityDTO> dtoList = pagedList.stream()
            .map(mapper::toDTO)
            .toList();

        // Step 5: Wrap into PagedModel (HATEOAS-style)
        return new PagedModel<>(
            new PageImpl<>(
                dtoList,
                pageable,
                total
            )
        );
    }

    @Override
    public PagedModel<ClubActivityDTO> getClubActivities(
        UUID clubId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            clubActivityRepository.findAllByClubId(clubId, pageable)
                .map(activity -> mapper.toDTO(activity,  ClubActivityDTO.class))
        );
    }

    @Override
    public PagedModel<UserActivityDTO> getUserActivities(
        UUID userId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            userActivityRepository.findAllByUserId(userId, pageable)
                .map(activity -> mapper.toDTO(activity,  UserActivityDTO.class))
        );
    }

}
