package com.intellexi.race_command_service.rest.dto.response;

import com.intellexi.race_command_service.enums.RaceDistance;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponseDto {

    UUID id;
    String name;
    RaceDistance distance;
    List<ApplicationResponseDto> applications;
}
