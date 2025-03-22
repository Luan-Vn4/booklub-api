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

    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="username")
    private String username;

    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;

}
