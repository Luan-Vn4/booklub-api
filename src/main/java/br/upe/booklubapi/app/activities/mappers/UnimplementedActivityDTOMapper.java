package br.upe.booklubapi.app.activities.mappers;

import br.upe.booklubapi.domain.activities.entities.Activity;

public class UnimplementedActivityDTOMapper extends RuntimeException {

    public UnimplementedActivityDTOMapper(Class<? extends Activity> clazz) {
        super(
            "Unimplemented mapper for activity of type %s"
                .formatted(clazz.getName())
        );
    }

}
