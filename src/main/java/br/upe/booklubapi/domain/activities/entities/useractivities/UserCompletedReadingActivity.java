package br.upe.booklubapi.domain.activities.entities.useractivities;

import br.upe.booklubapi.domain.books.entities.BookUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_completed_reading_activities")
@DiscriminatorValue("user_completed_reading_activity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCompletedReadingActivity extends UserActivity {

    @OneToOne
    @NotNull
    @JoinColumns({
        @JoinColumn(name="user_id", referencedColumnName="user_id"),
        @JoinColumn(name="book_id", referencedColumnName="book_id")
    })
    private BookUser bookUser;

}
