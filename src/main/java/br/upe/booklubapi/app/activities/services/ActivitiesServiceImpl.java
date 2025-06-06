package br.upe.booklubapi.app.activities.services;

import br.upe.booklubapi.app.activities.dtos.ActivityDTO;
import br.upe.booklubapi.app.activities.dtos.ClubActivityDTO;
import br.upe.booklubapi.app.activities.dtos.UserActivityDTO;
import br.upe.booklubapi.app.activities.mappers.ActivityDTOMapperResolver;
import br.upe.booklubapi.domain.activities.repositories.ActivityRepository;
import br.upe.booklubapi.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ActivitiesServiceImpl implements ActivitiesService {

    private final ActivityRepository activityRepository;

    private final ActivityDTOMapperResolver mapper;

    private final UserUtils userUtils;

    @Override
    public PagedModel<ActivityDTO> getActivitiesForUser(
        Pageable pageable
    ) {
        final UUID loggedUserId = userUtils.getLoggedUserId();
        return new PagedModel<>(
            activityRepository.findActivitiesForUser(loggedUserId, pageable)
                .map(mapper::toDTO)
        );
    }

    @Override
    public PagedModel<ClubActivityDTO> getClubActivities(
        UUID clubId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            activityRepository.findAllByClubId(clubId, pageable)
                .map(activity -> mapper.toDTO(activity,  ClubActivityDTO.class))
        );
    }

    @Override
    public PagedModel<UserActivityDTO> getUserActivities(
        UUID userId,
        Pageable pageable
    ) {
        return new PagedModel<>(
            activityRepository.findAllByUserId(userId, pageable)
                .map(activity -> mapper.toDTO(activity,  UserActivityDTO.class))
        );
    }

}
