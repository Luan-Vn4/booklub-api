package br.upe.booklubapi.app.books.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String description;
    private ImageLinks imageLinks;
    private String publishedDate;
    private List<IndustryIdentifier> industryIdentifiers;
}


