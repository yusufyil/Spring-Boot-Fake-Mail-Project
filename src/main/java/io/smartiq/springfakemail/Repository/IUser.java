package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User, Long> {
}
