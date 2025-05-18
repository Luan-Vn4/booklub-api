package br.upe.booklubapi.presentation.controllers.books;

import br.upe.booklubapi.app.books.dtos.BookItem;
import br.upe.booklubapi.app.books.dtos.BookItemQuery;
import br.upe.booklubapi.app.books.dtos.BookSearchResponse;
import br.upe.booklubapi.app.books.services.GoogleBooksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BooksController {

    private GoogleBooksService googleBooksService;

    @GetMapping("/search")
    public List<BookItem> searchResponse(BookItemQuery query){
        return googleBooksService.searchBooks(query);
    }


}
