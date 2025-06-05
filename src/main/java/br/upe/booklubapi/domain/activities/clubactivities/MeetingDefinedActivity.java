package br.upe.booklubapi.domain.activities.clubactivities;

import br.upe.booklubapi.domain.meetings.entities.Meeting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="meeting_defined_activities")
@DiscriminatorValue("meeting_defined_activity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDefinedActivity extends ClubActivity {

    @OneToOne
    @NotNull
    @JoinColumn(name="meeting_id")
    private Meeting meeting;

}
