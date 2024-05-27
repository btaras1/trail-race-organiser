package com.intellexi.racequery.event.strategy.race;

import com.intellexi.racequery.event.ApplicationEvent;
import com.intellexi.racequery.event.RaceEvent;
import com.intellexi.racequery.event.strategy.application.ApplicationDeleteEventStrategy;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import com.intellexi.racequery.service.ApplicationService;
import com.intellexi.racequery.service.RaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.intellexi.racequery.event.enums.EventType.DELETE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RaceDeleteEventStrategyTest {

    @Mock
    RaceService service;

    @InjectMocks
    RaceDeleteEventStrategy eventStrategy;

    @Test
    void isSatisfied() {
        RaceEvent event = RaceEvent.builder()
                .eventType(DELETE)
                .build();

        assertTrue(eventStrategy.isSatisfied(event));
    }

    @Test
    void handleEvent() {
        RaceEvent event = RaceEvent.builder()
                .request(RaceRequestDto.builder()
                        .id(UUID.randomUUID())
                        .build())
                .build();

        eventStrategy.handleEvent(event);

        verify(service, times(1)).delete(event.getRequest().getId());
    }
}