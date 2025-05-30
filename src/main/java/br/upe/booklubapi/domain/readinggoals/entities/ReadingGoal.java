package br.upe.booklubapi.domain.readinggoals.entities;

import br.upe.booklubapi.domain.clubs.entities.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(name="reading_goals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadingGoal {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="book_id")
    @NotNull
    @Size(max=32)
    private String bookId;

    @ManyToOne
    @JoinColumn(name="club_id")
    @NotNull
    private Club club;

    @Column(name="start_date")
    @NotNull
    LocalDate startDate;

    @Column(name="end_date")
    @NotNull
    LocalDate endDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ReadingGoal that = (ReadingGoal) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReadingGoal.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("bookId='" + bookId + "'")
            .add("clubId='" + club.getId() + "'")
            .add("startDate=" + startDate)
            .add("endDate=" + endDate)
            .toString();
    }

}
