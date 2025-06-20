package br.upe.booklubapi.presentation.controllers.users.activities;

import br.upe.booklubapi.app.activities.dtos.UserActivityDTO;
import br.upe.booklubapi.app.activities.services.ActivitiesService;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/activities")
@AllArgsConstructor
@Tag(name=ApiTag.ACTIVITIES)
public class UserActivitiesController {

    private final ActivitiesService activitiesService;

    @GetMapping
    @Operation(summary="Get activities from that user")
    public ResponseEntity<PagedModel<UserActivityDTO>> getUserActivities(
        @PathVariable(name="userId")
        UUID userId,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            activitiesService.getUserActivities(userId, pageable)
        );
    }

}
