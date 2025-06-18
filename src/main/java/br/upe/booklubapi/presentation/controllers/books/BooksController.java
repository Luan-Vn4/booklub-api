package br.upe.booklubapi.presentation.controllers.books;

import br.upe.booklubapi.app.books.dtos.BookItem;
import br.upe.booklubapi.app.books.dtos.BookItemQuery;
import br.upe.booklubapi.app.books.services.GoogleBooksService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BooksController {

    private GoogleBooksService googleBooksService;

    @PostMapping("/search")
    public PagedModel<BookItem> searchResponse(@RequestBody BookItemQuery query, Pageable pageable) {
        if (query == null || query.toString().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros da Query não podem ser nulos ou vazios!");
        }
        return googleBooksService.searchBooks(query, pageable);
    }

    @GetMapping("/{id}")
    public BookItem getBookById(@PathVariable String id) {
        return googleBooksService.getBookById(id);
    }

}
