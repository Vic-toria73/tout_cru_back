package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.breed.Breed;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id")
    private Breed breed;
}
