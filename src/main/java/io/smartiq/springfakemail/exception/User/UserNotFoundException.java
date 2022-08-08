package io.smartiq.springfakemail.exception.User;

import io.smartiq.springfakemail.exception.BaseEntityNotFoundException;

public class UserNotFoundException extends BaseEntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
