package io.smartiq.springfakemail.Exception.Role;

public class RoleNameAlreadyAddedException extends RuntimeException {
    public RoleNameAlreadyAddedException(String message) {
        super(message);
    }

    public RoleNameAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }
}
