package io.smartiq.springfakemail.Exception;

public abstract class BaseEntityNotFoundException extends RuntimeException{
    public BaseEntityNotFoundException(String message) {
        super(message);
    }

    public BaseEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
