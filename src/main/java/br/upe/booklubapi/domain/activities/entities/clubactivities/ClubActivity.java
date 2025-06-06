package br.upe.booklubapi.domain.activities.entities.clubactivities;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.clubs.entities.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name="club_activities")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="club_activity_type")
@DiscriminatorValue("club_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ClubActivity extends Activity {

    @ManyToOne(targetEntity=Club.class, fetch=FetchType.EAGER)
    @JoinColumn(name="club_id")
    @NotNull
    private UUID clubId;

}
