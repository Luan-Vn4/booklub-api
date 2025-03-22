package br.upe.booklubapi.app.DTOs.user;

import br.upe.booklubapi.domain.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
    }
}
