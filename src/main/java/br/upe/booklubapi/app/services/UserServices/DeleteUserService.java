package br.upe.booklubapi.app.services.UserServices;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.services.Command;
import br.upe.booklubapi.domain.entities.User;
import br.upe.booklubapi.domain.repositories.UserRepository;
import br.upe.booklubapi.presentation.exceptions.User.UserNotFoundException;

@Service
public class DeleteUserService implements Command<Integer, Void>{

    private UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        
        if(optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new UserNotFoundException();
    }
}