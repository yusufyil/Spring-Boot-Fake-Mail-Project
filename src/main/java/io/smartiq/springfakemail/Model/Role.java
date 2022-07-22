package io.smartiq.springfakemail.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @SequenceGenerator(name = "role_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
}
