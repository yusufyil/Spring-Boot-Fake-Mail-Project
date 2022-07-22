package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMail extends JpaRepository<Mail, Long> {
}
