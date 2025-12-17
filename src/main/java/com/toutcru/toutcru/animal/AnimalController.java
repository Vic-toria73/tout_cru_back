package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.animal.dto.AnimalCreateRequestDTO;
import com.toutcru.toutcru.animal.dto.AnimalResponseDTO;
import com.toutcru.toutcru.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private AnimalService animalService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> createAnimal(
            @RequestBody @Valid AnimalCreateRequestDTO dto
    ) {
        Long userId = userService.getCurrentUserId();
        AnimalResponseDTO created = animalService.createAnimal(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

//
//    @GetMapping("/me/{id}")
//
//    @PutMapping("/me/{id}")
//
//    @DeleteMapping("/me")

}
