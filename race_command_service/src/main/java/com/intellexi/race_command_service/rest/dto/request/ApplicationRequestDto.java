package com.intellexi.race_command_service.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestDto {

    private UUID id;
    @JsonProperty(required = true)
    private UUID user_id;
    private String club;
    @JsonProperty(required = true)
    private UUID race_id;
}
