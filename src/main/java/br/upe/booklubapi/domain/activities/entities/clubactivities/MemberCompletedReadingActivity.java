package br.upe.booklubapi.domain.activities.entities.clubactivities;

import br.upe.booklubapi.domain.books.entities.BookUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="member_completed_reading_activities")
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class MemberCompletedReadingActivity extends ClubActivity {

    @OneToOne
    @NotNull
    @JoinColumns({
        @JoinColumn(name="user_id", referencedColumnName="user_id"),
        @JoinColumn(name="book_id", referencedColumnName="book_id")
    })
    private BookUser bookUser;

}
