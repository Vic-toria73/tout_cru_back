package com.toutcru.toutcru.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByUserId(Long userId);
    Optional<Animal> findByIdAndUserId(Long id, Long userId);
}
