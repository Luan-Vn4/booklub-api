package br.upe.booklubapi.app.activities.mappers;

import br.upe.booklubapi.app.activities.dtos.ActivityDTO;
import br.upe.booklubapi.domain.activities.entities.Activity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ActivityDTOMapperResolver {

    private final List<ActivityDTOMapper<?>> mappers;

    public ActivityDTO toDTO(Activity activity) {
        for (ActivityDTOMapper<?> mapper : mappers) {
            if (mapper.canConvert(activity)) return mapper.toDTO(activity);
        }
        throw new UnimplementedActivityDTOMapper(activity.getClass());
    }

    public <T extends ActivityDTO> T toDTO(
        Activity activity,
        Class<T> targetClass
    ) {
        if (targetClass.isInstance(activity)) return targetClass.cast(activity);
        throw new IllegalArgumentException(
            "Mapped DTO is not type %s"
                .formatted(targetClass.getName())
        );
    }

}
