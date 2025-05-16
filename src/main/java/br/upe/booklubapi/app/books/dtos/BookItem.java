package br.upe.booklubapi.app.books.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookItem {
    private String title;
    private String authors;
    private String description;
    private String thumbnail;
    private String isbn;
}

