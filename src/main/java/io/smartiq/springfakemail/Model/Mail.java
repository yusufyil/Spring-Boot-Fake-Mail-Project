package io.smartiq.springfakemail.Model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
public class Mail {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String header;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private boolean isActive = true;
    @ManyToOne
    private User user;
}
