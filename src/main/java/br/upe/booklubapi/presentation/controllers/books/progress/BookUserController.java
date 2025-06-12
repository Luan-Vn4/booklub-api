package br.upe.booklubapi.presentation.controllers.books.progress;

import br.upe.booklubapi.app.books.dtos.BookUserDTO;
import br.upe.booklubapi.app.books.services.BookUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/book-user")
@AllArgsConstructor
public class BookUserController {

    private final BookUserService bookUserService;

    @PostMapping
    public ResponseEntity<BookUserDTO> create(
        @Valid
        @RequestBody
        BookUserDTO saveDTO
    ) {
        return ResponseEntity.ok(bookUserService.save(saveDTO));
    }

    @PutMapping
    public ResponseEntity<BookUserDTO> update(
        @Valid
        @RequestBody BookUserDTO updateDTO
    ) {
        return ResponseEntity.ok(bookUserService.update(updateDTO));
    }

    @DeleteMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<Void> delete(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("book-id")
        String bookId
    ) {
        bookUserService.delete(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<BookUserDTO> findById(
        @PathVariable("user-id")
        UUID userId,
        @PathVariable("book-id")
        String bookId
    ) {
        return ResponseEntity.ok(bookUserService.findById(userId, bookId));
    }

}
