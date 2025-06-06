package br.upe.booklubapi.domain.activities.entities.useractivities;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="user_activities")
@Inheritance(strategy= InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class UserActivity extends Activity {

    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    @NotNull
    private User user;

}
