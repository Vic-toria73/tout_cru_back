package com.toutcru.toutcru.animal.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AnimalUpdateRequestDTO {
    @Column
    private String name;
    @Column
    private LocalDate birth;
    @Column
    private Long pictureId;
    @Column
    private Double weight;
}
