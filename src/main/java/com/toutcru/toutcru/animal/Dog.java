package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.breed.Breed;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal{
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
}
