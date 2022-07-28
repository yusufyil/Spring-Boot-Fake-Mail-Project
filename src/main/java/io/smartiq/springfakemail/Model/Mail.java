package io.smartiq.springfakemail.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String header;
    @Column(nullable = false)
    String content;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    LocalDateTime updatedAt;
    @Column(nullable = false)
    boolean isActive = true;
    @ManyToOne
    User user;
}
