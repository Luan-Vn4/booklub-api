package br.upe.booklubapi.app.user.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.validators.UserValidator;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;

@Service
public class CreateUserService implements Command<User, UserDTO>{

    private UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDTO> execute(User user) {
        UserValidator.validate(user);

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(savedUser));
    }   
    
}
