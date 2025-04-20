package br.upe.booklubapi.domain.books.entities;

import java.util.UUID;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class BookUserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID bookId;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;
    
    @NotNull
    private float progress;
}
