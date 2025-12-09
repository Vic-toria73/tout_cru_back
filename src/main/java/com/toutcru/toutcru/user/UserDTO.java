package com.toutcru.toutcru.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @Size(min = 2, max = 255, message = "First name must be between 2 and 255 characters")
    private String firstname;

    @Email(message = "Email must be valid")
    private String email;
}