package br.upe.booklubapi.domain.users.entities;

import java.util.Map;
import java.util.UUID;
import br.upe.booklubapi.utils.hibernate.UUIDVarcharType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_entity")
public class User {

    @Id
    @Column(columnDefinition = "varchar(36)")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Type(UUIDVarcharType.class)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "user_attribute", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> attributes;

}
