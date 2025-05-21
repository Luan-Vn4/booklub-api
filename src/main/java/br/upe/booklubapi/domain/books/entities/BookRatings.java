package br.upe.booklubapi.domain.books.entities;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="book_ratings")
public class BookRatings {
    @EmbeddedId
    private BookUserId id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    @NotNull
    private User user;

    @NotNull
    @Range(min=0, max=5)
    private int rating;
    
    @NotNull
    @Range(min=0, max=5)
    private int dificulty;

    @NotNull
    @Size(max = 500)
    private String review;

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
}
