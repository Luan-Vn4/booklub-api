package br.upe.booklubapi.domain.activities.entities.clubactivities;

import br.upe.booklubapi.domain.books.entities.BookUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action=OnDeleteAction.CASCADE)
    private BookUser bookUser;

}
