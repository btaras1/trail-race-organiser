package com.intellexi.racequery.event.strategy.race;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.event.RaceEvent;
import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import com.intellexi.racequery.service.RaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.intellexi.racequery.event.enums.EventType.CREATE;
import static com.intellexi.racequery.event.enums.EventType.UPDATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RaceUpdateEventStrategyTest {

    @Mock
    RaceService service;
    @Mock
    RaceMapper mapper;

    @InjectMocks
    RaceUpdateEventStrategy eventStrategy;

    @Test
    void isSatisfied() {
        RaceEvent event = RaceEvent.builder()
                .eventType(UPDATE)
                .build();

        assertTrue(eventStrategy.isSatisfied(event));
    }

    @Test
    void handleEvent() {
        RaceEvent event = RaceEvent.builder()
                .request(RaceRequestDto.builder()
                        .build())
                .build();

        Race entity = Race.builder()
                .build();

        given(mapper.toEntity(event.getRequest())).willReturn(entity);

        eventStrategy.handleEvent(event);

        verify(mapper, times(1)).toEntity(event.getRequest());
        verify(service, times(1)).update(entity);
    }
}