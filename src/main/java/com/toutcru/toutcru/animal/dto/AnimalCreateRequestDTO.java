package com.toutcru.toutcru.animal.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Valid
public class AnimalCreateRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Column
    private String name;

    @NotBlank(message = "Species cannot be blank")
    @Column
    private String species;

    @Column
    private Long breedId;

    @Column
    private Long pictureId;

    @NotBlank(message = "Birth cannot be blank")
    @Past(message = "Birth must be in the past")
    @Column
    private LocalDate birth;

    @DecimalMin(value = "0.1", message = "weight must be greater than 0")
    @Column
    private BigDecimal weight;

//    private ActivityLevel activityLevel;
//    private LifeStage lifeStage;
//    private SpecialCondition specialCondition;
}
