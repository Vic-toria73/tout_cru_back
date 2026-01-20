package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserUpdatePasswordRequestDTO;
import com.toutcru.toutcru.user.dto.UserCreateRequestDTO;
import com.toutcru.toutcru.user.dto.UserResponseDTO;
import com.toutcru.toutcru.user.dto.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserCreateRequestDTO dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return mapToDTO(savedUser);
    }

    public UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }

    public Long getCurrentUserId() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserCustomDetails userDetails) {
            return userDetails.getId();
        }
        throw new AuthenticationCredentialsNotFoundException("Unauthenticated user");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserResponseDTO getMe() {
        User user = getUserById(getCurrentUserId());
        return mapToResponseDTO(user);
    }

    public UserResponseDTO updateMyAccount(UserUpdateRequestDTO request) {
        User user = getUserById(getCurrentUserId());

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        User savedUser = userRepository.save(user);
        return mapToResponseDTO(savedUser);
    }

    public void updateMyPassword (UserUpdatePasswordRequestDTO request) {
        User user = getUserById(getCurrentUserId());
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }
}
