package io.smartiq.springfakemail.Exception.Mail;

import io.smartiq.springfakemail.Exception.BaseEntityNotFoundException;

public class MailNotFoundException extends BaseEntityNotFoundException {
    public MailNotFoundException(String message) {
        super(message);
    }

    public MailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
