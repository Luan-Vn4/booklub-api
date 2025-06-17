package br.upe.booklubapi.app.books.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bouncycastle.jcajce.provider.symmetric.SM4;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookItem {
    private String id;
    private String title;
    private String authors;
    private String description;
    private String thumbnail;
    private String isbn;
    private String publishedDate;
}