package br.upe.booklubapi.domain.books.entities;
import br.upe.booklubapi.domain.books.entities.BookUserId;

import java.util.UUID;

import org.hibernate.validator.constraints.Range;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book_user_progress")
public class BookUserProgress {

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
}
