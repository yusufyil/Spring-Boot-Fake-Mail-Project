package io.smartiq.springfakemail.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleToUserForm {
    String username;
    String roleName;
}
