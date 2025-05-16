package br.upe.booklubapi.app.books.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndustryIdentifier {
    private String type;
    private String identifier;
}
