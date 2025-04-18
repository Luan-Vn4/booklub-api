package br.upe.booklubapi.domain.clubs.entities;

import br.upe.booklubapi.domain.users.entities.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
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

    @ManyToMany
    @JoinTable(
        name="clubs_users",
        joinColumns=@JoinColumn(name="club_id"),
        inverseJoinColumns=@JoinColumn(name="user_id")
    )
    @Setter(AccessLevel.PRIVATE)
    @NotNull
    private Set<User> members = new HashSet<>();

    public Club(
        UUID id,
        String name,
        LocalDate creationDate,
        @Nullable String imageUrl,
        Boolean isPrivate,
        User owner
    ) {
        this(
            id,
            name,
            creationDate,
            imageUrl,
            isPrivate,
            owner,
            new HashSet<>()
        );
    }

    public boolean addMember(User user) {
        members.add(user);
        if (user.isInClub(this)) return true;
        return user.joinClub(this);
    }

    public boolean removeMember(User user) {
        members.remove(user);
        if (!user.isInClub(this)) return true;
        return user.leaveClub(this);
    }

    public Set<User> getMembers(User user) {
        return Set.copyOf(members);
    }

    public boolean containsMember(User user) {
        return members.contains(user);
    }

    @PreRemove
    public void removeAllMembers() {
        for (User user : members) removeMember(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club other)) return false;
        return getId() != null
            && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Club.class.getSimpleName() + "[", "]")
            .add("id=" + getId())
            .add("name='" + getName() + "'")
            .add("creationDate=" + getCreationDate())
            .add("imageUrl='" + getImageUrl() + "'")
            .add("isPrivate=" + getIsPrivate())
            .add("ownerId=" + getOwner().getId())
            .toString();
    }

}
