package br.upe.booklubapi.domain.clubs.entities;

import br.upe.booklubapi.utils.hibernate.UUIDVarcharType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class ClubPendingEntryId {

    @Column(name="club_id")
    private UUID clubId;

    @Column(name="user_id")
    @Type(UUIDVarcharType.class)
    private UUID userId;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ClubPendingEntryId that)) return false;
        return clubId.equals(that.clubId)
            && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, userId);
    }

}
