package br.upe.booklubapi.domain.books.entities;

import br.upe.booklubapi.utils.hibernate.UUIDVarcharType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUserId {

    @Column(name="book_id")
    private String bookId;

    @Column(name="user_id", columnDefinition = "varchar(36)")
    @Type(UUIDVarcharType.class)
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BookUserId that)) return false;
        return bookId.equals(that.bookId)
            && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, userId);
    }
}
