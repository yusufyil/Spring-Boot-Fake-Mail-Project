package io.smartiq.springfakemail.exception;

import io.smartiq.springfakemail.exception.Role.RoleNameAlreadyAddedException;
import io.smartiq.springfakemail.exception.User.UsernameAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {BaseEntityNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(BaseEntityNotFoundException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UsernameAlreadyTakenException.class})
    public ResponseEntity<Object> handleUsernameException(UsernameAlreadyTakenException exception) {
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {RoleNameAlreadyAddedException.class})
    public ResponseEntity<Object> handleRoleNameException(RoleNameAlreadyAddedException exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
