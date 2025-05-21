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

import br.upe.booklubapi.app.books.dtos.BookClubProgressDTO;
import br.upe.booklubapi.app.books.dtos.BookUserProgressDTO;
import br.upe.booklubapi.app.books.services.BookProgressService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/book-progress")
@AllArgsConstructor
public class BookProgressController {
    private final BookProgressService bookProgressService;

    @PostMapping
    public ResponseEntity<BookUserProgressDTO> saveUserProgress(@RequestBody BookUserProgressDTO saveDTO) {
        return ResponseEntity.ok(bookProgressService.save(saveDTO));
    }

    @PutMapping
    public ResponseEntity<BookUserProgressDTO> updateUserProgress(@RequestBody BookUserProgressDTO updateProgressDTO) {
        return ResponseEntity.ok(bookProgressService.save(updateProgressDTO));
    }

    @DeleteMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<Void> deleteUserProgress(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        bookProgressService.delete(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<BookUserProgressDTO> getUserProgress(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        return ResponseEntity.ok(bookProgressService.findByUserId(userId, bookId));
    }
    
    @GetMapping("/club/{club-id}/book/{book-id}")
    public ResponseEntity<BookClubProgressDTO> getClubProgress(@PathVariable("club-id") UUID clubId, @PathVariable("book-id") UUID bookId) {
        return ResponseEntity.ok(bookProgressService.findByClubId(clubId, bookId));
    }
    
}
