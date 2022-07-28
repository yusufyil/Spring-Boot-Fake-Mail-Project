package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
}
