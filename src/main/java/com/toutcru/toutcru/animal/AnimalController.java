package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.animal.dto.AnimalCreateRequestDTO;
import com.toutcru.toutcru.animal.dto.AnimalResponseDTO;
import com.toutcru.toutcru.animal.dto.AnimalUpdateRequestDTO;
import com.toutcru.toutcru.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private AnimalService animalService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(@RequestBody @Valid AnimalCreateRequestDTO dto ) {
        Long userId = userService.getCurrentUserId();
        AnimalResponseDTO created = animalService.createAnimal(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AnimalResponseDTO>> getMyAnimals() {
        return ResponseEntity.ok(animalService.getMyAnimals());
    }

    @GetMapping("/me/{animalId}")
    public ResponseEntity<AnimalResponseDTO> getMyAnimal(@PathVariable Long animalId){
        return ResponseEntity.ok(animalService.getMyAnimal(animalId));
    }
//
//    @PutMapping("/me/{id}")
    @PutMapping("/me/{animalId}")
    public ResponseEntity<AnimalResponseDTO> updateMyAnimal(
            @PathVariable Long animalId,
            @RequestBody @Valid AnimalUpdateRequestDTO request ){
       AnimalResponseDTO result = animalService.updateMyAnimal(animalId, request);
    return ResponseEntity.ok(result);
}
//
//    @DeleteMapping("/me")
    @DeleteMapping("/me/{animalId}")
    public ResponseEntity<Void> deleteMyAnimal(@PathVariable Long animalId) {
        animalService.deleteMyAnimal(animalId);
        return ResponseEntity.noContent().build();
    }
}
