package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.rest.dto.response.UserDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserMapper {

    public UserDetailsResponseDto toDto(User user) {
        return UserDetailsResponseDto.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();
    }
}
