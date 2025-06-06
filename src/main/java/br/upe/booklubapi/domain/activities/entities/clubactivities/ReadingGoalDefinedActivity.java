package br.upe.booklubapi.domain.activities.entities.clubactivities;

import br.upe.booklubapi.domain.readinggoals.entities.ReadingGoal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="reading_goal_defined_activities")
@DiscriminatorValue("reading_goal_defined_activity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingGoalDefinedActivity extends ClubActivity {

    @OneToOne
    @NotNull
    @JoinColumn(name="reading_goal_id")
    private ReadingGoal readingGoal;

}
