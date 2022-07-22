package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUser extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
