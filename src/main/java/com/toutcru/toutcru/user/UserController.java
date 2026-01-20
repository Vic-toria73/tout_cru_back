package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserUpdatePasswordRequestDTO;
import com.toutcru.toutcru.user.dto.UserCreateRequestDTO;
import com.toutcru.toutcru.user.dto.UserResponseDTO;
import com.toutcru.toutcru.user.dto.UserUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyAccount() {
        return ResponseEntity.ok(userService.getMe());
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMyAccount(@RequestBody UserUpdateRequestDTO request) {
        return ResponseEntity.ok(userService.updateMyAccount(request));
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> updateMyPassword(@RequestBody @Valid UserUpdatePasswordRequestDTO request) { userService.updateMyPassword(request);
        return ResponseEntity.ok().build();
    }

}
