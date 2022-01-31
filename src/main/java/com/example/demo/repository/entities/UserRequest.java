package com.example.demo.repository.entities;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    /*
    this attribute is validated using regular express but it's much better using the annotation @Email
     */
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Error de formato de email")
    @NotNull(message = "email is required")
    private String email;

    @Pattern(regexp = "^(?=(?:\\D*\\d){2}\\D*$)(?:[^A-Z]*[A-Z]){1}[^A-Z]{7,11}$", message = "Error de formato de password")
    @NotNull(message = "password is required")
    private String password;
    private List<Phone> phones;

}
