package br.upe.booklubapi.domain.users.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    
    @Column(name="image_url")
    private String imageUrl;

    @NotNull @Email  @Size(max = 255)
    @Column(name="email")
    private String email;

    @NotNull @Size(max = 60)
    @Column(name="password")
    private String password;

    @NotNull @Size(max = 50)
    @Column(name="username")
    private String username;

    @NotNull @Size(max = 50)
    @Column(name="first_name")
    private String firstName;
    
    @NotNull @Size(max = 100)
    @Column(name="last_name")
    private String lastName;

}
