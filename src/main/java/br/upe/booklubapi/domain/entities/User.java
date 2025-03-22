package br.upe.booklubapi.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="image_url")
    private String image_url;

    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="username")
    private String username;

    @Column(name="first_name")
    private String first_name;
    
    @Column(name="last_name")
    private String last_name;
}
