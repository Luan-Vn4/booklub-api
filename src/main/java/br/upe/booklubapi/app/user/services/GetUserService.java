package br.upe.booklubapi.app.user.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.presentation.exceptions.User.UserNotFoundException;

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

        throw new UserNotFoundException();
    }
    
}
