package br.upe.booklubapi.app.validators;

import br.upe.booklubapi.domain.entities.User;
import br.upe.booklubapi.presentation.exceptions.User.EmailNotValidException;
import br.upe.booklubapi.presentation.exceptions.User.PasswordNotValidException;

public class UserValidator {
    private UserValidator() {
    }

    public static void validate(User user) {
        if(emailIsInvalid(user.getEmail())) {
            throw new EmailNotValidException();
        }

        if(passwordIsInvalid(user.getPassword())) {
            throw new PasswordNotValidException();
        }

    }

    public static boolean emailIsInvalid(String email) {
        String EMAIL_VERIFICATION = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return !email.matches(EMAIL_VERIFICATION);
    }

    public static boolean passwordIsInvalid(String password) {
        String PASSWORD_VERIFICATION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/`~]).{8,}$";
        return !password.matches(PASSWORD_VERIFICATION);
    }
}
