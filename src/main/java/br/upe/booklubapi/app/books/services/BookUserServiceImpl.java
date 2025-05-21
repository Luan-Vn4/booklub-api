package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookUserDTO;
import br.upe.booklubapi.app.books.dtos.BookUserDTOMapper;
import br.upe.booklubapi.app.books.dtos.UpdateBookUserDTOMapper;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookUser;
import br.upe.booklubapi.domain.books.repositories.BookProgressRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.domain.books.exceptions.BookUserNotFoundException;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class BookUserServiceImpl implements BookUserService {
    private final BookProgressRepository bookUserRepository;
    private final BookUserDTOMapper bookUserDTOMapper;
    private final UpdateBookUserDTOMapper updateBookUserDTOMapper;

    @Override
    public BookUserDTO save(BookUserDTO saveProgressDTO) {
        final BookUser progressEntity = bookUserDTOMapper.toEntity(saveProgressDTO);
        
        final BookUser savedProgress = bookUserRepository.save(progressEntity);

        return bookUserDTOMapper.toDTO(savedProgress);
    }

    @Override
    @Transactional
    public BookUserDTO update(BookUserDTO dto) {
        BookUserId id = new BookUserId(dto.userId(), dto.bookId());

        BookUser current = bookUserRepository.findById(id).orElseThrow(
            () -> new BookUserNotFoundException(id)
        );

        final BookUser updated = updateBookUserDTOMapper.partialUpdate(dto, current);

        bookUserRepository.save(current);

        return bookUserDTOMapper.toDTO(updated);
    }

    @Override
    public void delete(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        
        boolean progressExists = bookUserRepository.existsById(id);
        
        if (!progressExists)
        throw new BookUserNotFoundException(id);
        
        bookUserRepository.deleteById(id);
    }
    
    @Override
    public BookUserDTO findById(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        BookUser progress = bookUserRepository.findById(id)
        .orElseThrow(() -> new BookUserNotFoundException(id));
        
        return bookUserDTOMapper.toDTO(progress);
    }
}
