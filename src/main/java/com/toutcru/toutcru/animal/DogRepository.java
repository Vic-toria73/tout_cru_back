package com.toutcru.toutcru.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
    List<Dog> findAllByUserId(Long userId);
}
