package com.toutcru.toutcru.auth;

import com.toutcru.toutcru.auth.dto.LoginRequestDTO;
import com.toutcru.toutcru.auth.dto.LoginResponseDTO;
import com.toutcru.toutcru.configuration.JwtUtil;
import com.toutcru.toutcru.user.User;
import com.toutcru.toutcru.user.UserRepository;
import com.toutcru.toutcru.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Incorrect email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect email or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(jwtUtil.generateToken(user.getEmail()));
        response.setUser(userService.mapToDTO(user));
        return response;
    }
}
