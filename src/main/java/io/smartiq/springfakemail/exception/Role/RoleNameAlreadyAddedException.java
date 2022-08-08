package io.smartiq.springfakemail.exception.Role;

public class RoleNameAlreadyAddedException extends RuntimeException {
    public RoleNameAlreadyAddedException(String message) {
        super(message);
    }

    public RoleNameAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }
}
