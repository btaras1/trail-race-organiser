package com.intellexi.race_command_service.integration;

import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static reactor.core.publisher.Mono.just;

class ApplicationControllerIntegrationTest extends TestContainersInitializer {

    static UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";

    @Test
    void createApplication() {
        webTestClient.post()
                .uri("/api/v1/applications")
                .body(just(constructRequest()), ApplicationRequestDto.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private ApplicationRequestDto constructRequest() {
        return ApplicationRequestDto.builder()
                .id(APPLICATION_ID)
                .club(CLUB)
                .race_id(RACE_ID)
                .user_id(USER_ID)
                .build();
    }


}