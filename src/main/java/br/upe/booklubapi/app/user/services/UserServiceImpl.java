package br.upe.booklubapi.app.user.services;

import java.util.Optional;
import java.util.UUID;

import br.upe.booklubapi.app.user.dtos.UpdateUserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.UpdateUserDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.UserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.CreateUserDTOMapper;
import br.upe.booklubapi.app.user.dtos.mappers.UserDTOMapper;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import br.upe.booklubapi.presentation.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final CreateUserDTOMapper createUserDTOMapper;

    private final UpdateUserDTOMapper updateUserDTOMapper;

    private final UserDTOMapper userDTOMapper;

    private final UserRepository userRepository;

    private final UserMediaStorageService userMediaStorageService;
    
    @Override
    @Transactional
    public UserDTO create(CreateUserDTO userDTO) {
        final User user = userRepository.save(
            createUserDTOMapper.toEntity(userDTO)
        );
        user.setImageUrl(userMediaStorageService.saveProfilePicture(
            userDTO.image(),
            user.getId()
        ));
        return userDTOMapper.toDto(user);
    }

    @Override
    public UserDTO getByUuid(UUID uuid) {
        Optional<User> userOptional = userRepository.findById(uuid);
        if (userOptional.isEmpty()) throw new UserNotFoundException(uuid);

        return userDTOMapper.toDto(userOptional.get());
    }

    @Override
    public UserDTO getByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) throw new UserNotFoundException(email);

        return userDTOMapper.toDto(userOptional.get());
    }

    @Override
    @Transactional
    public UserDTO update(UpdateUserDTO updateUserDTO, UUID uuid) {
        final Optional<User> userOptional = userRepository.findById(
            uuid
        );
        if (userOptional.isEmpty())
            throw new UserNotFoundException(uuid);

        final User updatedUser = userRepository.save(
            updateUserDTOMapper.partialUpdate(
                updateUserDTO,
                userOptional.get()
            )
        );

        if (updateUserDTO.image() != null) {
            updatedUser.setImageUrl(userMediaStorageService.saveProfilePicture(
                updateUserDTO.image(),
                uuid
            ));
        }
        
        return userDTOMapper.toDto(updatedUser);
    }

    @Override
    public void delete(UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);

        if (user.isEmpty()) throw new UserNotFoundException(uuid);

        userRepository.deleteById(uuid);
    }
    
}
