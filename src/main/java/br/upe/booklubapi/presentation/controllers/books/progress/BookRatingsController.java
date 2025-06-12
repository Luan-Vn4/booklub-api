package br.upe.booklubapi.presentation.controllers.books.progress;

import br.upe.booklubapi.app.books.dtos.bookratings.BookRatingsDTO;
import br.upe.booklubapi.app.books.dtos.bookratings.CreateBookRatingsDTO;
import br.upe.booklubapi.app.books.dtos.bookratings.UpdateBookRatingsDTO;
import br.upe.booklubapi.app.books.services.BookRatingsService;
import br.upe.booklubapi.domain.books.entities.BookUserId;
import br.upe.booklubapi.utils.docs.ApiTag;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name=ApiTag.BOOK_RATINGS)
public class BookRatingsController {

    private final BookRatingsService bookRatingsService;

    @PostMapping("/api/v1/users/{userId}/book-ratings/{bookId}")
    public ResponseEntity<BookRatingsDTO> create(
        @Valid
        @RequestBody CreateBookRatingsDTO dto,
        @PathVariable("userId")
        UUID userId,
        @PathVariable("bookId")
        String bookId
    ) {
        return ResponseEntity.ok(
            bookRatingsService.save(new BookUserId(bookId, userId), dto)
        );
    }

    @PutMapping("/api/v1/users/{userId}/book-ratings/{bookId}")
    public ResponseEntity<BookRatingsDTO> update(
        @Valid
        @RequestBody UpdateBookRatingsDTO dto,
        @PathVariable("userId")
        UUID userId,
        @PathVariable("bookId")
        String bookId
    ) {
        return ResponseEntity.ok(
            bookRatingsService.update(new BookUserId(bookId, userId), dto)
        );
    }

    @DeleteMapping("api/v1/users/{userId}/book-ratings/{bookId}")
    public ResponseEntity<Void> delete(
        @PathVariable("userId")
        UUID userId,
        @PathVariable("bookId")
        String bookId
    ) {
        bookRatingsService.delete(new BookUserId(bookId, userId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("api/v1/users/{userId}/book-ratings/{bookId}")
    public ResponseEntity<BookRatingsDTO> findById(
        @PathVariable("userId")
        UUID userId,
        @PathVariable("bookId")
        String bookId
    ) {
        return ResponseEntity.ok(
            bookRatingsService.findById(new BookUserId(bookId, userId))
        );
    }

}
