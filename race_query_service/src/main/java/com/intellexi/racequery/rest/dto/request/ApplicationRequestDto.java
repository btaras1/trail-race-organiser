package com.intellexi.racequery.rest.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ApplicationRequestDto {

    private UUID id;
    private UUID userId;
    private String club;
    private UUID raceId;
}
