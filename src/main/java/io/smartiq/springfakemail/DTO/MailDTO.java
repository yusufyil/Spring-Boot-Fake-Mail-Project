package io.smartiq.springfakemail.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailDTO {
    private Long id;
    private String header;
    private String content;
    private Long userId;
}
