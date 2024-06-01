package com.intellexi.race_command_service.rest.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UserDetailsResponseDto {
    String email;
    String password;
    String role;
}
