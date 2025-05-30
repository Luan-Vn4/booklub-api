package br.upe.booklubapi.app.books.services;

import br.upe.booklubapi.app.books.dtos.*;
import br.upe.booklubapi.infra.core.gateways.GoogleBooks.GoogleBooksGateway;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {


    private final GoogleBooksGateway googleGateway;

    public GoogleBooksServiceImpl(GoogleBooksGateway googleGateway) {
        this.googleGateway = googleGateway;
    }

    @Override
    public PagedModel<BookItem> searchBooks(BookItemQuery query, Pageable pageable) {
        if (query == null || query.toString().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os parâmetros da Query não podem ser nulos ou vazios!\"");
        }

        Page<BookVolume> response = googleGateway.searchBooks(query.toString(), pageable);
        //conversão

        Page<BookItem> pageBookItem = response.map(this::convertToBookItem);
        return new PagedModel<>(pageBookItem);
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
