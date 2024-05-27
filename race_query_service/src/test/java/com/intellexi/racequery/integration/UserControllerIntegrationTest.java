package com.intellexi.racequery.integration;

import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerIntegrationTest extends TestContainersInitializer {

    static final String USER_ID = "ba55c96b-8969-4266-b931-e8f29976159c";

    @Test
    void getAllApplicationsByUserId_successfully() {
        List<ApplicationResponseDto> result = webTestClient.get()
                .uri("/api/v1/users/{id}/applications", USER_ID)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(ApplicationResponseDto.class)
                .getResponseBody()
                .collectList()
                .block();

        assertEquals(4, result.size());
    }

    @Test
    void getAllUnappliedRaces_successfully() {
        List<RaceResponseDto> result = webTestClient.get()
                .uri("/api/v1/users/{userId}/races/unapplied", USER_ID)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(RaceResponseDto.class)
                .getResponseBody()
                .collectList()
                .block();

        assertEquals(4, result.size());
    }
}
