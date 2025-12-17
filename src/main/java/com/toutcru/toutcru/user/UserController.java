package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserResponseDTO;
import com.toutcru.toutcru.user.dto.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getMyAccount() {
        return ResponseEntity.ok(userService.getMe());
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateMyAccount(
            @RequestBody UserUpdateRequestDTO request
    ) {
        return ResponseEntity.ok(userService.updateMyAccount(request));
    }

}



