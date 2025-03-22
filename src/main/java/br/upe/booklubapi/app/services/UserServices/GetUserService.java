package br.upe.booklubapi.app.services.UserServices;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.upe.booklubapi.app.DTOs.user.UserDTO;
import br.upe.booklubapi.app.services.Query;
import br.upe.booklubapi.domain.entities.User;
import br.upe.booklubapi.domain.repositories.UserRepository;

public class GetUserService implements Query<Integer, UserDTO>{

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDTO> execute(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            return ResponseEntity.ok(new UserDTO(userOptional.get()));
        }

        return null;
    }
    
}
