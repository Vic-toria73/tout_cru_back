package com.toutcru.toutcru.breed;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/breeds")
@RequiredArgsConstructor
public class BreedController {
    private final BreedService breedService;

    @GetMapping
    public ResponseEntity<List<Breed>> getAllBreeds(){
        return ResponseEntity.ok(breedService.getAllBreeds());
    }
}