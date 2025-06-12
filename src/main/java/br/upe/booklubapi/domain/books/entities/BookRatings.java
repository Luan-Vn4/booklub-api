package br.upe.booklubapi.domain.books.entities;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @Column(name = "rating")
    private Short rating;
    
    @NotNull
    @Range(min=0, max=5)
    @Column(name = "difficulty")
    private Short difficulty;

    @Nullable
    @Size(max = 500)
    @Column(name="review")
    private String review;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
