package br.upe.booklubapi.presentation.exceptions;

public enum ErrorMessages {
    EMAIL_NOT_VALID("The Email Is Not Found"),
    PASSWORD_NOT_VALID("User Not Found"),
    USER_NOT_FOUND("User Not Found"),
    USERNAME_MUST_NOT_BE_EMPTY("Username Field Must Not Be Empty"),
    FIRST_NAME_MUST_NOT_BE_EMPTY("First Name Field Must Not Be Empty"),
    LAST_NAME_MUST_NOT_BE_EMPTY("Last Name Field Must Not Be Empty");

    private String message;

    ErrorMessages(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
