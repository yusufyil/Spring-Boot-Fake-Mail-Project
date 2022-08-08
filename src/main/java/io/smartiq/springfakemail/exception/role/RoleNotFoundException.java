package io.smartiq.springfakemail.exception.role;

import io.smartiq.springfakemail.exception.BaseEntityNotFoundException;

public class RoleNotFoundException extends BaseEntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
