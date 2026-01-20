package com.toutcru.toutcru.animal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnimalResponseDTO {

    private Long id;
    private String name;
    private String species;  // DOG, CAT
    private LocalDate birth;
    private BigDecimal weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
