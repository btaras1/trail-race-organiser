package com.intellexi.race_command_service.rest.dto.request;

import com.intellexi.race_command_service.enums.RaceDistance;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceRequestDto {
    private UUID id;
    private String name;
    private RaceDistance distance;
}
