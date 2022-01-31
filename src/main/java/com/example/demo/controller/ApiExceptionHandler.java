package com.example.demo.controller;

import com.example.demo.repository.dao.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;
import java.time.Instant;

import static java.sql.Timestamp.from;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        return ErrorDTO.builder().codigo(fieldError.hashCode()).detail(defaultMessage).timestamp(from(Instant.now())).build();
    }

    @ExceptionHandler({SignatureException.class})
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorDTO handleSignatureError(SignatureException ex) {
        return ErrorDTO.builder().codigo(ex.hashCode()).detail(ex.getMessage()).timestamp(from(Instant.now())).build();
    }
}
