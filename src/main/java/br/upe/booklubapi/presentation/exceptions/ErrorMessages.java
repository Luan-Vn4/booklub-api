package br.upe.booklubapi.presentation.exceptions;

public enum ErrorMessages {
    EMAIL_NOT_VALID("The Email Is Not Found"),
    PASSWORD_NOT_VALID("User Not Found"),
    USER_NOT_FOUND("User Not Found"),;

    private String message;

    ErrorMessages(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
