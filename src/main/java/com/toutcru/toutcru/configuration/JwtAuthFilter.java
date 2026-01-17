package com.toutcru.toutcru.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("=== JWT Filter ===");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        System.out.println("Header Authorization: " + header);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring((7));
            System.out.println("Token extrait: " + token.substring(0, 20) + "...");

            String email = jwtUtil.getEmailFromToken(token);
            System.out.println("Email du token: " + email);

            if (jwtUtil.validateToken(token, email)) {
                System.out.println("Token valide !");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Token INVALIDE !");
            }
        }
        filterChain.doFilter(request, response);
    }
}