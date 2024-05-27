package com.intellexi.racequery.service.auth;

import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.exception.UserNotFoundException;
import com.intellexi.racequery.repository.UserRepository;
import com.intellexi.racequery.rest.dto.request.AuthRequestDto;
import com.intellexi.racequery.rest.dto.response.AuthResponseDto;
import com.intellexi.racequery.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    static final String USER_NOT_FOUND_MESSAGE = "User with email %s not found.";
    static final String EMAIL = "email@example.com";
    static final String PASSWORD = "password";
    static final String JWT_TOKEN = "jwt-token";
    static final Long JWT_EXPIRATION = 1000L;
    @Mock
    UserRepository userRepository;
    @Mock
    JwtService jwtService;
    @Mock
    AuthenticationManager authenticationManager;

    @InjectMocks
    AuthService authService;

    @Test
    void authenticate() {
        AuthRequestDto requestDto = constructRequest();
        User user = User.builder()
                .email(EMAIL)
                .build();

        given(userRepository.findByEmail(EMAIL))
                .willReturn(Optional.of(user));
        given(jwtService.generateToken(user)).willReturn(JWT_TOKEN);
        given(jwtService.getExpirationTime()).willReturn(JWT_EXPIRATION);

        AuthResponseDto result = authService.authenticate(requestDto);

        assertEquals(JWT_TOKEN, result.getToken());
        assertEquals(JWT_EXPIRATION, result.getExpiresIn());
    }

    @Test
    void authenticate_userNotFound_throwsException() {
        AuthRequestDto requestDto = constructRequest();

        given(userRepository.findByEmail(EMAIL))
                .willReturn(Optional.empty());

        Exception exception = assertThrows(
                UserNotFoundException.class,
                () -> authService.authenticate(requestDto));

        assertEquals(exception.getMessage(), constructUserNotFoundExceptionMessage(EMAIL));
    }

    private AuthRequestDto constructRequest() {
        return AuthRequestDto.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    private String constructUserNotFoundExceptionMessage(String email) {
        return String.format("User with email %s not found.", email);
    }
}