package com.toutcru.toutcru.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   private Long getCurrentUserId(){
       UserCustomDetails userDetails = (UserCustomDetails) SecurityContextHolder
               .getContext()
               .getAuthentication()
               .getPrincipal();
       return userDetails.getId();
   }

    //récuperer le user
    @GetMapping("/me")
    public ResponseEntity<User> getMyAccount() {
        return ResponseEntity.ok(userService.getMe(getCurrentUserId()));
    }

    //modifier le user connecté
    @PutMapping ("/me")
    public ResponseEntity<User> updateMyAccount(@RequestBody UserDTO request) {
        return ResponseEntity.ok(userService.updateMyAccount(getCurrentUserId(), request));
    }

}



