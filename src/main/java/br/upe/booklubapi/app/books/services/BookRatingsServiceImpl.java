package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookRatingsDTO;
import br.upe.booklubapi.app.books.dtos.BookRatingsDTOMapper;
import br.upe.booklubapi.app.books.dtos.UpdateBookRatingsDTOMapper;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.books.repositories.BookRatingsRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.domain.books.exceptions.BookRatingsNotFoundException;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class BookRatingsServiceImpl implements BookRatingsService {
    private final BookRatingsRepository bookRatingsRepository;
    private final BookRatingsDTOMapper bookRatingsDTOMapper;
    private final UpdateBookRatingsDTOMapper updateBookRatingsDTOMapper;

    @Override
    public BookRatingsDTO save(BookRatingsDTO saveDTO) {
        final BookRatings ratingsEntity = bookRatingsDTOMapper.toEntity(saveDTO);
        
        final BookRatings saved = bookRatingsRepository.save(ratingsEntity);

        return bookRatingsDTOMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public BookRatingsDTO update(BookRatingsDTO dto) {
        BookUserId id = new BookUserId(dto.userId(), dto.bookId());

        BookRatings current = bookRatingsRepository.findById(id).orElseThrow(
            () -> new BookRatingsNotFoundException(id)
        );

        final BookRatings updated = updateBookRatingsDTOMapper.partialUpdate(dto, current);

        bookRatingsRepository.save(current);

        return bookRatingsDTOMapper.toDTO(updated);
    }

    @Override
    public void delete(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        
        boolean ratingsExist = bookRatingsRepository.existsById(id);
        
        if (!ratingsExist) throw new BookRatingsNotFoundException(id);
        
        bookRatingsRepository.deleteById(id);
    }
    
    @Override
    public BookRatingsDTO findById(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        
        BookRatings ratings = bookRatingsRepository.findById(id)
        .orElseThrow(() -> new BookRatingsNotFoundException(id));
        
        return bookRatingsDTOMapper.toDTO(ratings);
    }
}
