package io.smartiq.springfakemail.repository;

import io.smartiq.springfakemail.model.Mail;
import io.smartiq.springfakemail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    List<Mail> findMailByUser(User user);
}
