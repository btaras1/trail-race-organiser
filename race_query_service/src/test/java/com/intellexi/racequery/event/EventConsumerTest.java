package com.intellexi.racequery.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellexi.racequery.event.strategy.application.ApplicationDeleteEventStrategy;
import com.intellexi.racequery.event.strategy.race.RaceDeleteEventStrategy;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import com.intellexi.racequery.service.ApplicationService;
import com.intellexi.racequery.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.intellexi.racequery.event.enums.EventType.DELETE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventConsumerTest {

    ApplicationDeleteEventStrategy applicationDeleteEventStrategy;
    RaceDeleteEventStrategy raceDeleteEventStrategy;
    @Mock
    ApplicationService applicationService;
    @Mock
    RaceService raceService;
    @Mock
    ObjectMapper objectMapper;

    EventConsumer eventConsumer;

    @BeforeEach
    void setUp() {
        applicationDeleteEventStrategy = new ApplicationDeleteEventStrategy(applicationService);
        raceDeleteEventStrategy = new RaceDeleteEventStrategy(raceService);

        eventConsumer = new EventConsumer(
                List.of(applicationDeleteEventStrategy, raceDeleteEventStrategy),
                objectMapper);
    }

    @Test
    void consumeRaceEvents() throws Exception {
        String message = "raceEvent";
        RaceEvent raceEvent = RaceEvent.builder()
                .request(RaceRequestDto.builder()
                        .id(UUID.randomUUID())
                        .build())
                .eventType(DELETE)
                .build();

        given(objectMapper.readValue(message, RaceEvent.class))
                .willReturn(raceEvent);

        eventConsumer.consumeRaceEvents(message);

        verify(raceService, times(1)).delete(raceEvent.getRequest().getId());
    }

    @Test
    void consumeApplicationEvents() throws Exception {
        String message = "applicationEvent";
        ApplicationEvent applicationEvent = ApplicationEvent.builder()
                .request(ApplicationRequestDto.builder()
                        .id(UUID.randomUUID())
                        .build())
                .eventType(DELETE)
                .build();

        given(objectMapper.readValue(message, ApplicationEvent.class))
                .willReturn(applicationEvent);

        eventConsumer.consumeApplicationEvents(message);

        verify(applicationService, times(1)).delete(applicationEvent.getRequest().getId());
    }
}