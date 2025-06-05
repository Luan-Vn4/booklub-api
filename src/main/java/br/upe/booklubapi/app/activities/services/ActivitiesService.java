package br.upe.booklubapi.app.activities.services;

import br.upe.booklubapi.app.activities.dtos.ActivityDTO;
import org.springframework.data.web.PagedModel;

import java.util.UUID;

public interface ActivitiesService {

    // TODO A ideia desse método seria buscar as atividades que interessam
    //      àquele usuário, juntando as atividades dos clubes que ele participa
    //      e dos amigos dele
    PagedModel<ActivityDTO> getActivitiesForUser(UUID userId);

    PagedModel<ActivityDTO> getClubActivities(UUID clubId);

    PagedModel<ActivityDTO> getUserActivities(UUID userId);

}
