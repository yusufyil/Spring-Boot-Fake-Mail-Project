package io.smartiq.springfakemail.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    Long id;
    String name;
    boolean isActive;
}
