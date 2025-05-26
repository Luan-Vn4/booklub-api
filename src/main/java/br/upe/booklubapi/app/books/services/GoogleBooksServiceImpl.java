package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.*;
import br.upe.booklubapi.infra.core.gateways.GoogleBooks.GoogleBooksGateway;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {


    private final GoogleBooksGateway googleGateway;

    public GoogleBooksServiceImpl(GoogleBooksGateway googleGateway) {
        this.googleGateway = googleGateway;
    }


    @Override
    public List<BookItem> searchBooks(BookItemQuery query) {
        if (query == null || query.toString().isBlank()) {
            return Collections.emptyList();
        }
        BookSearchResponse response = googleGateway.searchBooks(query.toString());
        //conversão
        return response.getItems().stream()
                .map(this::convertToBookItem)
                .toList();
    }

    //a gnt transforma para poder enviar um json pro front mais simplificado do q recebemos da api
    private BookItem convertToBookItem(BookVolume volume) {
        VolumeInfo info = volume.getVolumeInfo();

        String title = info.getTitle();
        String authors = info.getAuthors() != null ? String.join(", ", info.getAuthors()) : null;
        String description = info.getDescription();
        String thumbnail = info.getImageLinks() != null ? info.getImageLinks().getThumbnail() : null;
        //esse aq pega o 1 da lista, q pode n ser o mais atualizado/comum
        String isbn = info.getIndustryIdentifiers() != null && !info.getIndustryIdentifiers().isEmpty()
                ? info.getIndustryIdentifiers().get(0).getIdentifier()
                : null;

        BookItem item = new BookItem();
        item.setTitle(title);
        item.setAuthors(authors);
        item.setDescription(description);
        item.setThumbnail(thumbnail);
        item.setIsbn(isbn);

        return item;
    }
}
