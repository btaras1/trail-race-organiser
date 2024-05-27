package com.intellexi.racequery.event.strategy.application;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.event.ApplicationEvent;
import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.intellexi.racequery.event.enums.EventType.CREATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationCreateEventStrategyTest {

    @Mock
    ApplicationService service;
    @Mock
    ApplicationMapper mapper;

    @InjectMocks
    ApplicationCreateEventStrategy eventStrategy;

    @Test
    void isSatisfied() {
        ApplicationEvent event = ApplicationEvent.builder()
                .eventType(CREATE)
                .build();

        assertTrue(eventStrategy.isSatisfied(event));
    }

    @Test
    void handleEvent() {
        ApplicationEvent event = ApplicationEvent.builder()
                .request(ApplicationRequestDto.builder()
                        .build())
                .build();

        Application entity = Application.builder()
                .build();

        given(mapper.toEntity(event.getRequest())).willReturn(entity);

        eventStrategy.handleEvent(event);

        verify(mapper, times(1)).toEntity(event.getRequest());
        verify(service, times(1)).create(entity);
    }
}