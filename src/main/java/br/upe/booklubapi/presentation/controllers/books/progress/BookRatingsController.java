package br.upe.booklubapi.presentation.controllers.books.progress;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.booklubapi.app.books.dtos.BookRatingsDTO;
import br.upe.booklubapi.app.books.services.BookRatingsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/book-ratings")
@AllArgsConstructor
public class BookRatingsController {
    private final BookRatingsService bookRatingsService;

    @PostMapping
    public ResponseEntity<BookRatingsDTO> create(@RequestBody BookRatingsDTO saveDTO) {
        return ResponseEntity.ok(bookRatingsService.save(saveDTO));
    }

    @PutMapping
    public ResponseEntity<BookRatingsDTO> update(@RequestBody BookRatingsDTO updateDTO) {
        return ResponseEntity.ok(bookRatingsService.update(updateDTO));
    }

    @DeleteMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        bookRatingsService.delete(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<BookRatingsDTO> findById(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        return ResponseEntity.ok(bookRatingsService.findById(userId, bookId));
    }
}
