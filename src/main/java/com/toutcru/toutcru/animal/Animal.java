package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.animal.enums.ActivityLevel;
import com.toutcru.toutcru.animal.enums.LifeStage;
import com.toutcru.toutcru.breed.Breed;
import com.toutcru.toutcru.user.User;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "species", discriminatorType = DiscriminatorType.STRING)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @Column(nullable = true)
    private Long pictureId;

    @Column
    private LocalDate birth;

    @Column
    private BigDecimal weight;

    @Column
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column
    @Enumerated(EnumType.STRING)
    private LifeStage lifeStage;

    @Column(columnDefinition = "TEXT")
    private String treatments;

    @Column (name = "ration_id")
    private Long rationId;

    @Column (name = "current_ration_id")
    private  Long currentRationId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
