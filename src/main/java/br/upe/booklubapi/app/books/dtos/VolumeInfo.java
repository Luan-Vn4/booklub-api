package br.upe.booklubapi.app.books.dtos;

import java.util.List;

public class VolumeInfo {
    public String title;
    public List<String> authors;
    public String description;
    public ImageLinks imageLinks;
    public List<IndustryIdentifier> industryIdentifiers;
}
