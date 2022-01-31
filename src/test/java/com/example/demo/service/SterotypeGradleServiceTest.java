package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.dao.LoginDTO;
import com.example.demo.repository.dao.UserDTO;
import com.example.demo.repository.entities.Phone;
import com.example.demo.repository.entities.UserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import com.example.demo.repository.entities.User;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SterotypeGradleServiceTest {

    @Mock
    private UserRepository userRepository;

    private SterotypeGradleService service;

    private final String EMAIL = "ejemplo@gmail.com";

    private  User user;

    @BeforeEach
    public void setUp() {

        user = User.builder().id(1L)
                .created(LocalDate.now())
                .lastLogin(LocalDate.now())
                .token("TOKEN")
                .name("rafael")
                .email(EMAIL)
                .password("Rafael22")
                .build();

        service = new SterotypeGradleService(userRepository);
    }

    @Test
    void getUserByEmail(){

        when(userRepository.findUserByEmail(EMAIL)).thenReturn(user);

        Optional<LoginDTO> loginDTO = service.getUserByEmail(EMAIL);

        Assertions.assertTrue(loginDTO.isPresent());

    }

    @Test
    void createNewUserAlreadyExist(){

        UserRequest userRequest = UserRequest.builder().name("rafael").email(EMAIL).build();

        when(userRepository.findUserByNameAndAndEmail(userRequest.getName(), userRequest.getEmail())).thenReturn(user);

        Optional<UserDTO> userDTO = service.createNewUser(userRequest);

        Assertions.assertTrue(userDTO.isEmpty());

    }
}