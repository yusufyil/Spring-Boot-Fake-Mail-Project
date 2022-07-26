package io.smartiq.mailproducer.model;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO implements Serializable {
    Long id;
    String header;
    String content;
    boolean isActive;
    Long userId;
}
