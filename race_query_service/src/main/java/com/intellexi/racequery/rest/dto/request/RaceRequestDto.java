package com.intellexi.racequery.rest.dto.request;


import com.intellexi.racequery.domain.enums.RaceDistance;
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
