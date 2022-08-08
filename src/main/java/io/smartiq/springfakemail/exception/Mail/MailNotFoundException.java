package io.smartiq.springfakemail.exception.Mail;

import io.smartiq.springfakemail.exception.BaseEntityNotFoundException;

public class MailNotFoundException extends BaseEntityNotFoundException {
    public MailNotFoundException(String message) {
        super(message);
    }

    public MailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
