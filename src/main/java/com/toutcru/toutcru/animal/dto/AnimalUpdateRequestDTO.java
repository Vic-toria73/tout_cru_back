package com.toutcru.toutcru.animal.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnimalUpdateRequestDTO {
    @Column
    private Long pictureId;

    @Column
    private Long birth;

    @Column
    private BigDecimal weight;
}
