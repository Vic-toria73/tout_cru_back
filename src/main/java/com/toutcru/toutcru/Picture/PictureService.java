package com.toutcru.toutcru.Picture;

import com.toutcru.toutcru.animal.Animal;
import com.toutcru.toutcru.animal.AnimalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final AnimalRepository animalRepository;

    public Picture addPictureToAnimal(Long animalId, String url, String altText, String userEmail ) {
        //récupere animal
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        // vérifier animal a un user
        if (!animal.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized: This animal doesn't belong to you");
        }
        // création + sauvegarder picture
        Picture picture = new Picture();
        picture.setUrl(url);
        picture.setAltText(altText);
        picture.setAnimal(animal);

        return pictureRepository.save(picture);
    }
    public Picture getPictureById(Integer pictureId) {
        return pictureRepository.findById(pictureId)
                .orElseThrow(() -> new RuntimeException("Picture not found with id: " + pictureId));
    }

    public void deletePicture(Integer pictureId, String userEmail) {
        Picture picture = getPictureById(pictureId);

        // verif que la pic appartient à l'animal du user
        if (!picture.getAnimal().getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized: You can't delete this picture");
        }

        pictureRepository.delete(picture);
    }

    public Picture updatePicture(Integer pictureId, String url, String altText, String userEmail) {
        Picture picture = getPictureById(pictureId);
        //verif l'autorisation
        if (!picture.getAnimal().getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized: You can't update this picture");
        }

        picture.setUrl(url);
        picture.setAltText(altText);

        return pictureRepository.save(picture);
    }
}
