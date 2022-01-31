package com.example.demo.repository.dao;

import com.example.demo.repository.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements ResponseDTO{
    private Long id;
    private LocalDate created;
    private LocalDate lastlogin;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
