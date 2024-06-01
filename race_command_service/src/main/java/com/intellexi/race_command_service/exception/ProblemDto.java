package com.intellexi.race_command_service.exception;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Data
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProblemDto {
    String message;
    Integer code;
}
