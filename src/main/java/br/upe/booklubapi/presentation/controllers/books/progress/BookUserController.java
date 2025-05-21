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

import br.upe.booklubapi.app.books.dtos.BookUserDTO;
import br.upe.booklubapi.app.books.services.BookUserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/book-user")
@AllArgsConstructor
public class BookUserController {
    private final BookUserService bookUserService;

    @PostMapping
    public ResponseEntity<BookUserDTO> create(@RequestBody BookUserDTO saveDTO) {
        return ResponseEntity.ok(bookUserService.save(saveDTO));
    }

    @PutMapping
    public ResponseEntity<BookUserDTO> update(@RequestBody BookUserDTO updateDTO) {
        return ResponseEntity.ok(bookUserService.update(updateDTO));
    }

    @DeleteMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        bookUserService.delete(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{user-id}/book/{book-id}")
    public ResponseEntity<BookUserDTO> findById(@PathVariable("user-id") UUID userId, @PathVariable("book-id") UUID bookId) {
        return ResponseEntity.ok(bookUserService.findById(userId, bookId));
    }
}
