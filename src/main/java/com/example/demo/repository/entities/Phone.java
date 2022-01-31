package com.example.demo.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "phone")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "phone_sequence", initialValue = 1, allocationSize = 1)
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "phone_sequence")
    private Long id;

    private long number;

    @Column(name = "city_code", nullable = false)
    private int cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

}
