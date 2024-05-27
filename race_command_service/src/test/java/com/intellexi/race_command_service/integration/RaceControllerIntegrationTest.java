package com.intellexi.race_command_service.integration;

import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import com.intellexi.race_command_service.rest.dto.response.RaceResponseDto;
import org.junit.jupiter.api.Test;

import static com.intellexi.race_command_service.enums.RaceDistance.FIVEKM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reactor.core.publisher.Mono.just;

class RaceControllerIntegrationTest extends TestContainersInitializer {

    static final String RACE_NAME = "5k";

    @Test
    void createRace() {
        RaceRequestDto request = constructRequest();

        RaceResponseDto result = webTestClient
                .post()
                .uri("/api/v1/races")
                .body(just(request), RaceRequestDto.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(RaceResponseDto.class)
                .getResponseBody()
                .blockFirst();

        assertEquals(request.getName(), result.getName());
        assertEquals(request.getDistance(), result.getDistance());
    }

    private RaceRequestDto constructRequest() {
        return RaceRequestDto.builder()
                .name(RACE_NAME)
                .distance(FIVEKM)
                .build();
    }
}