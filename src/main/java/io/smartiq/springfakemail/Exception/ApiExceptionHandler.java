package io.smartiq.springfakemail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BaseEntityNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(BaseEntityNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                exception.getMessage(),
                httpStatus
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
