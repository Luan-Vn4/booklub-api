package br.upe.booklubapi.domain.books.entities;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book_user")
public class BookUser {

    @EmbeddedId
    private BookUserId id;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    @MapsId("userId")
    @NotNull
    private User user;
    
    @NotNull
    @Range(min=0, max=1)
    private Double progress;

    public boolean hasFinishedBook() {
        return progress.equals(1.0);
    }

}
