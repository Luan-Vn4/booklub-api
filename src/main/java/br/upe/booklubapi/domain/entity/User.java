package br.upe.booklubapi.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
