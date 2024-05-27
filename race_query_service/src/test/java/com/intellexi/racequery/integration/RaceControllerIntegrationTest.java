package com.intellexi.racequery.integration;

import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceControllerIntegrationTest extends TestContainersInitializer {

    static final String RACE_ID = "3ea17f4a-00f8-4112-a728-0620962c3f2a";
    @Test
    void getByRaceId_successfully() {
        RaceResponseDto result = webTestClient.get()
                .uri("/api/v1/races/{id}", RACE_ID)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(RaceResponseDto.class)
                .getResponseBody()
                .blockFirst();

        assertEquals(UUID.fromString(RACE_ID), result.getId());
    }

    @Test
    void getAllRaces_successfully() {
        List<RaceResponseDto> result = webTestClient.get()
                .uri("/api/v1/races")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(RaceResponseDto.class)
                .getResponseBody()
                .collectList()
                .block();

        assertEquals(8, result.size());
    }
}
