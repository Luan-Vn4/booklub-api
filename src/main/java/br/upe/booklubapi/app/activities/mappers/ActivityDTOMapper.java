package br.upe.booklubapi.app.activities.mappers;

import br.upe.booklubapi.app.activities.dtos.ActivityDTO;
import br.upe.booklubapi.domain.activities.entities.Activity;

public interface ActivityDTOMapper<T extends ActivityDTO> {

    T toDTO(Activity activity);

    boolean canConvert(Activity activity);

}
