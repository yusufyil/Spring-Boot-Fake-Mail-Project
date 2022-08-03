package io.smartiq.springfakemail.DTO;

import io.smartiq.springfakemail.Model.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    Long id;
    String name;
    String surname;
    String username;
    String password;
    boolean isActive;
    Collection<Role> roles;
}
