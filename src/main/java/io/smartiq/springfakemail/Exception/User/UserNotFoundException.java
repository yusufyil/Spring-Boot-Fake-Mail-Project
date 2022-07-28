package io.smartiq.springfakemail.Exception.User;

import io.smartiq.springfakemail.Exception.BaseEntityNotFoundException;

public class UserNotFoundException extends BaseEntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
