package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.bookratings.*;
import br.upe.booklubapi.domain.books.entities.BookRatings;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.exceptions.BookRatingsNotFoundException;
import br.upe.booklubapi.domain.books.repositories.BookRatingsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookRatingsServiceImpl implements BookRatingsService {

    private final BookRatingsRepository bookRatingsRepository;

    private final BookRatingsDTOMapper bookRatingsDTOMapper;

    private final CreateBookRatingsDTOMapper createBookRatingsDTOMapper;

    private final UpdateBookRatingsDTOMapper updateBookRatingsDTOMapper;

    @Override
    @Transactional
    public BookRatingsDTO save(BookUserId bookUserId, CreateBookRatingsDTO dto) {
        final BookRatings ratingsEntity = createBookRatingsDTOMapper.toEntity(
            dto,
            bookUserId
        );
        
        final BookRatings saved = bookRatingsRepository.save(ratingsEntity);

        return bookRatingsDTOMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public BookRatingsDTO update(
        BookUserId bookUserId,
        UpdateBookRatingsDTO dto
    ) {
        BookRatings current = bookRatingsRepository.findById(bookUserId)
            .orElseThrow(() -> new BookRatingsNotFoundException(bookUserId)
        );

        final BookRatings updated = bookRatingsRepository.save(
            updateBookRatingsDTOMapper.partialUpdate(
                dto,
                current,
                bookUserId.getUserId(),
                bookUserId.getBookId()
            )
        );

        return bookRatingsDTOMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(BookUserId bookUserId) {
        boolean ratingsExist = bookRatingsRepository.existsById(bookUserId);
        
        if (!ratingsExist) throw new BookRatingsNotFoundException(bookUserId);
        
        bookRatingsRepository.deleteById(bookUserId);
    }
    
    @Override
    public BookRatingsDTO findById(BookUserId bookUserId) {
        BookRatings ratings = bookRatingsRepository.findById(bookUserId)
        .orElseThrow(() -> new BookRatingsNotFoundException(bookUserId));
        return bookRatingsDTOMapper.toDTO(ratings);
    }

}
