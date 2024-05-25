package com.intellexi.racequery.rest.dto.response;

import com.intellexi.racequery.domain.enums.RaceDistance;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class RaceResponseDto {

    UUID id;
    String name;
    RaceDistance distance;
    List<ApplicationResponseDto> applications;
}
