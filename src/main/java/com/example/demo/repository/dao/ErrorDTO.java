package com.example.demo.repository.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements ResponseDTO{
    private int codigo;
    private Timestamp timestamp;
    private String detail;
}
