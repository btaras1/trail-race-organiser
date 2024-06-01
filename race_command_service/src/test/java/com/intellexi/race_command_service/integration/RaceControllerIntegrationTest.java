package com.intellexi.race_command_service.integration;

import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import com.intellexi.race_command_service.rest.dto.response.RaceResponseDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.RaceDistance.FIVEKM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static reactor.core.publisher.Mono.just;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class RaceControllerIntegrationTest {

    static final String RACE_NAME = "5k";
    static UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";
    @Autowired
    private WebTestClient webTestClient;

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"))
            .withEnv("KAFKA_CREATE_TOPICS", "races:1:3,applications:1:3");

    @BeforeAll
    public static void beforeAll() {
        kafka.start();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", () -> kafka.getBootstrapServers());
    }

    @AfterAll
    public static void afterAll() {
        kafka.stop();
    }


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

    @Test
    void createApplication() {
        webTestClient.post()
                .uri("/api/v1/applications")
                .body(just(constructApplicationRequest()), ApplicationRequestDto.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private ApplicationRequestDto constructApplicationRequest() {
        return ApplicationRequestDto.builder()
                .id(APPLICATION_ID)
                .club(CLUB)
                .race_id(RACE_ID)
                .user_id(USER_ID)
                .build();
    }

    private RaceRequestDto constructRequest() {
        return RaceRequestDto.builder()
                .name(RACE_NAME)
                .distance(FIVEKM)
                .build();
    }
}