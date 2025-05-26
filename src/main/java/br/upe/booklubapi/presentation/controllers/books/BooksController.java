package br.upe.booklubapi.presentation.controllers.books;

import br.upe.booklubapi.app.books.dtos.BookItem;
import br.upe.booklubapi.app.books.dtos.BookItemQuery;
import br.upe.booklubapi.app.books.dtos.BookSearchResponse;
import br.upe.booklubapi.app.books.services.GoogleBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BooksController {

    private GoogleBooksService googleBooksService;

    @GetMapping("/search")
    public List<BookItem> searchResponse(@RequestBody BookItemQuery query){
        if (query == null || query.toString().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros da Query não podem ser nulos ou vazios!");
        }
        return googleBooksService.searchBooks(query);
    }


}
