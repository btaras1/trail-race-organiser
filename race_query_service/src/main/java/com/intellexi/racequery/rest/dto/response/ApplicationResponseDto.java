package com.intellexi.racequery.rest.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class ApplicationResponseDto {

    UUID id;
    String firstName;
    String lastName;
    String club;
}
