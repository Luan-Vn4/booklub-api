package br.upe.booklubapi.app.books.dtos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookSearchResponse {
    private List<BookVolume> items;
}
