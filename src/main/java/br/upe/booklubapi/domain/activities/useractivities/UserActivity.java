package br.upe.booklubapi.domain.activities.useractivities;

import br.upe.booklubapi.domain.activities.Activity;
import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name="user_activities")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="user_activity_type")
@DiscriminatorValue("user_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserActivity extends Activity {

    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    @NotNull
    private UUID userId;

}
