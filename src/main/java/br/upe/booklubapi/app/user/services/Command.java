package br.upe.booklubapi.app.user.services;

import org.springframework.http.ResponseEntity;

public interface Command<I, O> {
    public ResponseEntity<O> execute(I input);
}