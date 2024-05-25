package com.intellexi.racequery.rest.dto.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class AuthRequestDto {

    String email;
    String password;
}
