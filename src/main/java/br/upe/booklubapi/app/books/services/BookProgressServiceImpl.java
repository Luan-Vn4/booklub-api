package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.BookClubProgressDTO;
import br.upe.booklubapi.app.books.dtos.BookUserProgressDTO;
import br.upe.booklubapi.app.books.dtos.BookUserProgressDTOMapper;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.domain.books.entities.BookUserProgress;
import br.upe.booklubapi.domain.books.repositories.BookProgressRepository;
import br.upe.booklubapi.domain.clubs.exceptions.ClubNotFoundException;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.booklubapi.domain.books.exceptions.ProgressNotFoundException;
import br.upe.booklubapi.domain.clubs.repositories.ClubRepository;

@Service
@AllArgsConstructor
public class BookProgressServiceImpl implements BookProgressService {
    private final BookProgressRepository bookProgressRepository;
    private final BookUserProgressDTOMapper bookUserProgressDTOMapper;
    private final ClubRepository clubRepository;

    @Override
    public BookUserProgressDTO save(BookUserProgressDTO saveProgressDTO) {
        final BookUserProgress progressEntity = bookUserProgressDTOMapper.toEntity(saveProgressDTO);
        
        final BookUserProgress savedProgress = bookProgressRepository.save(progressEntity);

        return bookUserProgressDTOMapper.toDTO(savedProgress);
    }

    @Override
    public void delete(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        
        boolean progressExists = bookProgressRepository.existsById(id);
        
        if (!progressExists)
        throw new ProgressNotFoundException(id);
        
        bookProgressRepository.deleteById(id);
    }
    
    @Override
    public BookUserProgressDTO findByUserId(UUID userId, UUID bookId) {
        BookUserId id = new BookUserId(userId, bookId);
        BookUserProgress progress = bookProgressRepository.findById(id)
        .orElseThrow(() -> new ProgressNotFoundException(id));
        
        return bookUserProgressDTOMapper.toDTO(progress);
    }
    
    @Override
    public BookClubProgressDTO findByClubId(UUID clubId, UUID bookId) {
        boolean clubExists = clubRepository.existsById(clubId);
        if(!clubExists) throw new ClubNotFoundException(clubId);

        Double clubProgress = bookProgressRepository.findByClubId(clubId, bookId);
        return new BookClubProgressDTO(clubId, bookId, clubProgress.doubleValue());
    }
}
