package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.animal.dto.AnimalCreateRequestDTO;
import com.toutcru.toutcru.animal.dto.AnimalResponseDTO;
import com.toutcru.toutcru.user.User;
import com.toutcru.toutcru.user.UserRepository;
import jakarta.persistence.DiscriminatorValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    public AnimalResponseDTO createAnimal(AnimalCreateRequestDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Animal animal = createAnimalBySpecies(dto);

        animal.setUserId(user);
        animal.setName(dto.getName());
        animal.setBirth(dto.getBirth());
        animal.setWeight(dto.getWeight());

        Animal savedAnimal = animalRepository.save(animal);

        return mapToDTO(savedAnimal);
    }

    private Animal createAnimalBySpecies(AnimalCreateRequestDTO dto) {
        return switch (dto.getSpecies()){
            case "DOG" -> {
                Dog dog = new Dog();
                dog.setBreedId(dto.getBreedId());
                yield dog;
            }
            // CAT ?

            default -> throw new IllegalArgumentException("Unknown species" + dto.getSpecies());
        };
    }

    private AnimalResponseDTO mapToDTO(Animal animal) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getClass().getAnnotation(DiscriminatorValue.class).value());
        dto.setBirth(animal.getBirth());
        dto.setWeight(animal.getWeight());

        return dto;
    }
}



    //CRUD
    // get animal via user_id
    // User update these animals
    //delete animal

