package com.intellexi.race_command_service.exception;

import lombok.Builder;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProblemDto {
    String message;
    Integer code;
}
