package com.intellexi.racequery.event.strategy.application;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.event.ApplicationEvent;
import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.intellexi.racequery.event.enums.EventType.DELETE;
import static com.intellexi.racequery.event.enums.EventType.UPDATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationDeleteEventStrategyTest {

    @Mock
    ApplicationService service;

    @InjectMocks
    ApplicationDeleteEventStrategy eventStrategy;

    @Test
    void isSatisfied() {
        ApplicationEvent event = ApplicationEvent.builder()
                .eventType(DELETE)
                .build();

        assertTrue(eventStrategy.isSatisfied(event));
    }

    @Test
    void handleEvent() {
        ApplicationEvent event = ApplicationEvent.builder()
                .request(ApplicationRequestDto.builder()
                        .id(UUID.randomUUID())
                        .build())
                .build();

        eventStrategy.handleEvent(event);

        verify(service, times(1)).delete(event.getRequest().getId());
    }
}