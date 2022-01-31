package com.example.demo.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "user_demo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "user_demo_sequence", initialValue = 1, allocationSize = 1)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_demo_sequence")
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Phone> phones;

    private LocalDate created;

    @Column(name = "last_login", nullable = false)
    private LocalDate lastLogin;

    private String token;

}
