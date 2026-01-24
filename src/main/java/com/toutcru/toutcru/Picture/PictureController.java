package com.toutcru.toutcru.Picture;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @PostMapping("/animal/{animalId}")
    public ResponseEntity<Picture> addPicture(
            @PathVariable Long animalId,
            @RequestBody Picture pictureData,
            Authentication authentication) {

        String userEmail = authentication.getName();

        Picture picture = pictureService.addPictureToAnimal(
                animalId,
                pictureData.getUrl(),
                pictureData.getAltText(),
                userEmail
        );

        return ResponseEntity.ok(picture);
    }

    @DeleteMapping("/{pictureId}")
    public ResponseEntity<Void> deletePicture(
            @PathVariable Integer pictureId,
            Authentication authentication) {

        pictureService.deletePicture(pictureId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pictureId}")
    public ResponseEntity<Picture> updatePicture(
            @PathVariable Integer pictureId,
            @RequestBody Picture pictureData,
            Authentication authentication) {

        Picture updated = pictureService.updatePicture(
                pictureId,
                pictureData.getUrl(),
                pictureData.getAltText(),
                authentication.getName()
        );

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{pictureId}")
    public ResponseEntity<Picture> getPicture(@PathVariable Integer pictureId) {
        return ResponseEntity.ok(pictureService.getPictureById(pictureId));
    }
}

