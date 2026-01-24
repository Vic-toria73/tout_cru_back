package com.toutcru.toutcru.breed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;

    public List<Breed> getAllBreeds(){
        return breedRepository.findAll();
    }
}
