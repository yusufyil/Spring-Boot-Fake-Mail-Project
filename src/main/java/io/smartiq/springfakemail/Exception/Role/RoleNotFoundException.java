package io.smartiq.springfakemail.Exception.Role;

import io.smartiq.springfakemail.Exception.BaseEntityNotFoundException;

public class RoleNotFoundException extends BaseEntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
