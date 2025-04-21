package br.upe.booklubapi.domain.books.entities;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="book_user_rating")
public class BookUserRating {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @NotNull
    @Column(name = "book_id")
    private UUID bookId;

    @NotNull
    private int rating;

    @NotNull
    private int dificulty;

    @NotNull
    @Size(max = 256)
    private String review;

    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
