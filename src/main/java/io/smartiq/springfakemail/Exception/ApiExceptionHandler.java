package io.smartiq.springfakemail.Exception;

import io.smartiq.springfakemail.Exception.User.UsernameAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
    @ExceptionHandler(value = {BaseEntityNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(BaseEntityNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
    @ExceptionHandler(value = {UsernameAlreadyTakenException.class})
    public ResponseEntity<Object> handleUsernameException(UsernameAlreadyTakenException exception){
        HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
