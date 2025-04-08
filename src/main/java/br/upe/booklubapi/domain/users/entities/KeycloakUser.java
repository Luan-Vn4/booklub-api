package br.upe.booklubapi.domain.users.entities;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeycloakUser {
    private UUID id;
    
    private String email;

    private String password;

    private String username;

    private String firstName;
    
    private String lastName;

    Map<String, List<String>> attributes;
}
