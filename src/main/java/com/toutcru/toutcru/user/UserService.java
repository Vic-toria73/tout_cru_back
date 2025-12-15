package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getMe(Long userId) {
        return getUserById(userId);
    }

    public User updateMyAccount(Long userId, UserResponseDTO request) {
        User user = getUserById(userId);

        if (request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if (request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        return userRepository.save(user);
    }

}
