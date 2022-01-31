package com.example.demo.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements ResponseDTO{
    private Long id;
    private LocalDate date;
    private LocalDate lastlogin;
    private String token;
    private boolean isActive;
}
