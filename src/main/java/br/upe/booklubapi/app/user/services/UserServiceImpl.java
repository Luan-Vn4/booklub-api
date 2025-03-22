package br.upe.booklubapi.app.user.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.app.user.dtos.CreateUserDTO;
import br.upe.booklubapi.app.user.dtos.mappers.CreateUserMapper;
import br.upe.booklubapi.domain.users.entities.User;
import br.upe.booklubapi.domain.users.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private CreateUserMapper createUserMapper;
    private UserRepository userRepository;
    
    @Override
    public CreateUserDTO create(CreateUserDTO userDTO) {    
        User user = createUserMapper.toEntity(userDTO);

        return createUserMapper.toDto(userRepository.save(user));
    }

    @Override
    public Optional<CreateUserDTO> getByUuid(UUID uuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUuid'");
    }

    @Override
    public Optional<CreateUserDTO> getByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
    }

    @Override
    public CreateUserDTO update(CreateUserDTO dto, UUID uuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(UUID uuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
