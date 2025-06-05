package br.upe.booklubapi.domain.activities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="activities")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="activity_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Activity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private LocalDateTime createdAt;


}
