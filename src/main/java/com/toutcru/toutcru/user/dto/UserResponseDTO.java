package com.toutcru.toutcru.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String email;

}
