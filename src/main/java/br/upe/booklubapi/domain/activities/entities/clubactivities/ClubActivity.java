package br.upe.booklubapi.domain.activities.entities.clubactivities;

import br.upe.booklubapi.domain.activities.entities.Activity;
import br.upe.booklubapi.domain.clubs.entities.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="club_activities")
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class ClubActivity extends Activity {

    @ManyToOne(targetEntity=Club.class, fetch=FetchType.EAGER)
    @JoinColumn(name="club_id")
    @NotNull
    private Club club;

}
