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
    
    @Column("image_url")
    private String image_url;

    @Column("email")
    private String email;
    
    @Column("password")
    private String password;
    
    @Column("username")
    private String username;

    @Column("first_name")
    private String first_name;
    
    @Column("last_name")
    private String last_name;
}
