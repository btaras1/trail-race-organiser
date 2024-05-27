package com.intellexi.racequery.integration;

import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationControllerIntegrationTest extends TestContainersInitializer {

    static final String APPLICATION_ID = "84094138-a0dd-425b-93cc-c15a2eca4d27";
    @Test
    void getByApplicationId_successfully() {
        ApplicationResponseDto result = webTestClient.get()
                .uri("/api/v1/applications/{id}", APPLICATION_ID)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(ApplicationResponseDto.class)
                .getResponseBody()
                .blockFirst();

        assertEquals(UUID.fromString(APPLICATION_ID), result.getId());
    }

    @Test
    void getAllApplications_successfully() {
        List<ApplicationResponseDto> result = webTestClient.get()
                .uri("/api/v1/applications")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(ApplicationResponseDto.class)
                .getResponseBody()
                .collectList()
                .block();

        assertEquals(16, result.size());
    }
}
