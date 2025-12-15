package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   //function is in service or not ?
   private Long getCurrentUserId(){
       Object principal = SecurityContextHolder
               .getContext()
               .getAuthentication()
               .getPrincipal();
       if (principal instanceof UserCustomDetails userDetails){
           return userDetails.getId();
       }
       throw new AuthenticationCredentialsNotFoundException("Utilisateur non authentifié");
   }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyAccount() {
        return ResponseEntity.ok(userService.getMe(getCurrentUserId()));
    }

    @PutMapping ("/me")
    public ResponseEntity<UserResponseDTO> updateMyAccount(@RequestBody UserResponseDTO request) {
        return ResponseEntity.ok(userService.updateMyAccount(getCurrentUserId(), request));
    }

}



