package com.toutcru.toutcru.auth.dto;

import com.toutcru.toutcru.user.dto.UserResponseDTO;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UserResponseDTO user;
}
