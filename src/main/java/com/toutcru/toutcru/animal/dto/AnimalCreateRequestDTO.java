package com.toutcru.toutcru.animal.dto;

import com.toutcru.toutcru.animal.enums.ActivityLevel;
import com.toutcru.toutcru.animal.enums.LifeStage;
import com.toutcru.toutcru.animal.enums.SpecialCondition;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Activity level cannot be null")
    private ActivityLevel activityLevel;

    @NotNull(message = "Life stage cannot be null")
    private LifeStage lifeStage;


}
