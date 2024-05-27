package com.intellexi.racequery.service.auth;

import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.exception.UserNotFoundException;
import com.intellexi.racequery.repository.UserRepository;
import com.intellexi.racequery.rest.dto.request.AuthRequestDto;
import com.intellexi.racequery.rest.dto.response.AuthResponseDto;
import com.intellexi.racequery.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthResponseDto authenticate(AuthRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User authenticatedUser = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with email %s not found.", input.getEmail())));

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return AuthResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
