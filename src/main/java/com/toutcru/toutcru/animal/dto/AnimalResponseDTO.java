package com.toutcru.toutcru.animal.dto;

import com.toutcru.toutcru.animal.enums.ActivityLevel;
import com.toutcru.toutcru.animal.enums.LifeStage;
import com.toutcru.toutcru.breed.dto.BreedResponseDTO;
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
    private Double weight;
    private ActivityLevel activityLevel;
    private LifeStage lifeStage;
    private String treatments;
    private BreedResponseDTO breed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
