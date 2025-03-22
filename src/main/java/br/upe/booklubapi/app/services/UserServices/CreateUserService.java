package br.upe.booklubapi.app.services.UserServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.DTOs.user.UserDTO;
import br.upe.booklubapi.app.services.Command;
import br.upe.booklubapi.domain.entities.User;
import br.upe.booklubapi.domain.repositories.UserRepository;

@Service
public class CreateUserService implements Command<User, UserDTO>{

    private UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserDTO> execute(User user) {
        
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(savedUser));
    }   
    
}
