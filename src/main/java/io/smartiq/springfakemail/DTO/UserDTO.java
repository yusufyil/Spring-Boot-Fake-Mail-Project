package io.smartiq.springfakemail.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
}
