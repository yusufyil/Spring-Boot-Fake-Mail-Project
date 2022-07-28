package io.smartiq.springfakemail.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private Long id;
    private String header;
    private String content;
    private boolean isActive;
    private Long userId;
}
