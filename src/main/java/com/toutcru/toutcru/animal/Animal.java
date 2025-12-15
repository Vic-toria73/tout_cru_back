package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column
    private Long breedId;

    @Column
    private Long pictureId;

    @Column
    private Long birth;

    @Column
    private BigDecimal weight;

    @Column
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column
    @Enumerated(EnumType.STRING)
    private LifeStage lifeStage;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialCondition specialCondition;

    @Column
    private Text treatments;

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
