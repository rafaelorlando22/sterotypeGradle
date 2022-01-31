package com.example.demo.controller;

import com.example.demo.repository.dao.ErrorDTO;
import com.example.demo.repository.dao.LoginDTO;
import com.example.demo.repository.dao.ResponseDTO;
import com.example.demo.repository.dao.UserDTO;
import com.example.demo.repository.entities.User;
import com.example.demo.repository.entities.UserRequest;
import com.example.demo.service.SterotypeGradleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

import static java.sql.Timestamp.from;


@RestController
@RequestMapping("/sterotype/v1")
public class SterotypeGradleController {

    private SterotypeGradleService service;

    @Autowired
    public SterotypeGradleController(SterotypeGradleService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> getData(@RequestBody UserRequest userRequest) {
        Optional<LoginDTO> loginDTO = null;

         loginDTO = service.getUserByEmail(userRequest.getEmail());

        return loginDTO.isEmpty()
                ? ResponseEntity.unprocessableEntity().body(ErrorDTO.builder().detail("usuario no existe").timestamp(from(Instant.now())).build())
                : ResponseEntity.ok().body(loginDTO.get());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDTO> save(
            @RequestBody @Validated UserRequest userRequest) {
        Optional<UserDTO> userDTO = null;

        userDTO = service.createNewUser(userRequest);

        return userDTO.isPresent()
                ? ResponseEntity.ok().body(userDTO.get())
                : ResponseEntity.unprocessableEntity().body(ErrorDTO.builder().detail("usuario ya existe").timestamp(from(Instant.now())).build());
    }
}
