package br.upe.booklubapi.app.validators;

import br.upe.booklubapi.domain.entities.User;
import br.upe.booklubapi.presentation.exceptions.ErrorMessages;
import br.upe.booklubapi.presentation.exceptions.User.UserNotValidException;
import io.micrometer.common.util.StringUtils;

public class UserValidator {
    private UserValidator() {
    }

    public static void validate(User user) {

        if(StringUtils.isEmpty(user.getUsername())) {
            throw new UserNotValidException(ErrorMessages.USERNAME_MUST_NOT_BE_EMPTY.getMessage());
        }

        if(StringUtils.isEmpty(user.getFirst_name())) {
            throw new UserNotValidException(ErrorMessages.FIRST_NAME_MUST_NOT_BE_EMPTY.getMessage());
        }

        if(StringUtils.isEmpty(user.getLast_name())) {
            throw new UserNotValidException(ErrorMessages.LAST_NAME_MUST_NOT_BE_EMPTY.getMessage());
        }

        if(emailIsInvalid(user.getEmail())) {
            throw new UserNotValidException(ErrorMessages.EMAIL_NOT_VALID.getMessage());
        }
        
        if(passwordIsInvalid(user.getPassword())) {
            throw new UserNotValidException(ErrorMessages.PASSWORD_NOT_VALID.getMessage());
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
