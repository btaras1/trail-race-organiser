package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.exception.ApplicationNotFoundException;
import com.intellexi.racequery.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    static final UUID USER_ID = UUID.randomUUID();
    static final String EMAIL = "EMAIL";
    static final String FIRST_NAME = "FIRST_NAME";
    static final String LAST_NAME = "LAST_NAME";
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService service;

    @Test
    void findByEmail() {
        User entity = constructUser();

        given(userRepository.findByEmail(EMAIL)).willReturn(Optional.of(entity));

        UserDetails result = service.findByEmail(EMAIL);

        assertEquals(entity, result);
    }

    @Test
    void findById() {
        User entity = constructUser();

        given(userRepository.findById(USER_ID)).willReturn(Optional.of(entity));

        User result = service.findById(USER_ID);

        assertEquals(entity, result);
    }

    private User constructUser() {
        return User.builder()
                .id(USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();
    }
}