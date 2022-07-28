package io.smartiq.springfakemail.DTO;

import io.smartiq.springfakemail.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    boolean isActive;
    private Collection<Role> roles;
}
