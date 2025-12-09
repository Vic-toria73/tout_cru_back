package com.toutcru.toutcru.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getMe(Long userId) {
        return getUserById(userId);
    }

    public User updateMyAccount(Long userId, UserDTO request) {
        User user = getUserById(userId);

        if (request.getFirstname() != null){
            user.setFirstName(request.getFirstname());
        }
        if (request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        return userRepository.save(user);
    }

}
