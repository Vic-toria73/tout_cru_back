package com.toutcru.toutcru.user;

import com.toutcru.toutcru.user.dto.UserResponseDTO;
import com.toutcru.toutcru.user.dto.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseDTO userResponseDTO;


    public Long getCurrentUserId(){
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserCustomDetails userDetails){
            return userDetails.getId();
        }
        throw new AuthenticationCredentialsNotFoundException("Unauthenticated user");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User getMe() {
        return getUserById(getCurrentUserId());
    }

    public User updateMyAccount(UserUpdateRequestDTO request) {
        User user = getUserById(getCurrentUserId());

        if (request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if (request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        return userRepository.save(user);
    }

}
