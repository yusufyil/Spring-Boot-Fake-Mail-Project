package io.smartiq.springfakemail.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {

    Long id;
    String header;
    String content;
    boolean isActive;
    Long userId;
}
