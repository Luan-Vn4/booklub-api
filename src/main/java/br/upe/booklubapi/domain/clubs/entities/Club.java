package br.upe.booklubapi.domain.clubs.entities;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="clubs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="club_name", unique=true)
    @NotNull
    @Size(max=50)
    private String name;

    @Column(name="creation_date")
    @NotNull
    private LocalDate creationDate;

    @Column(name="image_url")
    @Nullable
    private String imageUrl;

    @Column(name="is_private")
    @NotNull
    private Boolean isPrivate = false;

    @ManyToOne
    @JoinColumn(name="owner_id")
    @NotNull
    private User owner;

}
