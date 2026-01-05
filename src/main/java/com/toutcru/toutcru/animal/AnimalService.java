package com.toutcru.toutcru.animal;

import com.toutcru.toutcru.animal.dto.AnimalCreateRequestDTO;
import com.toutcru.toutcru.animal.dto.AnimalResponseDTO;
import com.toutcru.toutcru.animal.dto.AnimalUpdateRequestDTO;
import com.toutcru.toutcru.user.User;
import com.toutcru.toutcru.user.UserRepository;
import com.toutcru.toutcru.user.UserService;
import jakarta.persistence.DiscriminatorValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public AnimalResponseDTO createAnimal(AnimalCreateRequestDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Animal animal = createAnimalBySpecies(dto);

        animal.setUser(user);
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

            default -> throw new IllegalArgumentException("Unknown species: " + dto.getSpecies());
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

    public List<AnimalResponseDTO> getMyAnimals() {
        Long currentUserId = userService.getCurrentUserId();
        List<Animal> animals = animalRepository.findAllByUser_Id(currentUserId);
        return animals.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public AnimalResponseDTO getMyAnimal(Long animalId) {
        Long currentUserId = userService.getCurrentUserId();
        Animal animal = animalRepository.findByIdAndUser_Id(animalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        return mapToDTO(animal);
    }

    public AnimalResponseDTO updateMyAnimal(Long animalId, AnimalUpdateRequestDTO dto) {
        Long currentUserId = userService.getCurrentUserId();
        Animal animal = animalRepository.findByIdAndUser_Id(animalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        if (dto.getName() != null) {
            animal.setName(dto.getName());
        }
        if (dto.getBirth() != null) {
            animal.setBirth(dto.getBirth());
        }
        if (dto.getPictureId() !=null) {
            animal.setPictureId(dto.getPictureId());
        }

        Animal savedAnimal = animalRepository.save(animal);
        return mapToDTO(savedAnimal);
    }


    public void deleteMyAnimal(Long animalId) {
        Long currentUserId = userService.getCurrentUserId();
        Animal animal = animalRepository.findByIdAndUser_Id(animalId, currentUserId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
                animalRepository.delete(animal);
    }
}

