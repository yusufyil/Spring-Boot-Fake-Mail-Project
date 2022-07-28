package io.smartiq.springfakemail.DTO;

import io.smartiq.springfakemail.Model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    Long id;
    String name;
    String surname;
    String username;
    String password;
    boolean isActive;
    Collection<Role> roles;
}
