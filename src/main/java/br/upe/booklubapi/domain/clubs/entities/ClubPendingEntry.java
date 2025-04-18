package br.upe.booklubapi.domain.clubs.entities;

import br.upe.booklubapi.domain.clubs.entities.enums.EntryType;
import br.upe.booklubapi.domain.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="club_pending_entries")
@NoArgsConstructor
@Getter
@Setter
public class ClubPendingEntry {

    @EmbeddedId
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private ClubPendingEntryId id;

    @ManyToOne
    @MapsId("clubId")
    @JoinColumn(name="club_id")
    private Club club;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name="entry_type")
    @NotNull
    private EntryType entryType;

    public ClubPendingEntry(
        @NotNull Club club,
        @NotNull User user,
        @NotNull EntryType entryType
    ) {
        this.id = new ClubPendingEntryId(club.getId(), user.getId());
        this.club = club;
        this.user = user;
        this.entryType = entryType;
    }

    public void setClub(Club club) {
        this.id.setClubId(club.getId());
        this.club = club;
    }

    public void setUser(User user) {
        this.id.setUserId(user.getId());
        this.user = user;
    }

}


