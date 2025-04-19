package br.upe.booklubapi.domain.clubs.exceptions;

public class RequestAlreadySentException extends RuntimeException {
    public RequestAlreadySentException(String message) {
        super(message);
    }
}
