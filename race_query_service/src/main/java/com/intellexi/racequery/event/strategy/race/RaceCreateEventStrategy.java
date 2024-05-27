package com.intellexi.racequery.event.strategy.race;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.event.RaceEvent;
import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.event.strategy.EventStrategy;
import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.intellexi.racequery.event.enums.EventType.CREATE;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceCreateEventStrategy implements EventStrategy<RaceEvent> {

    RaceService service;
    RaceMapper mapper;

    @Override
    public boolean isSatisfied(Object event) {
        return event instanceof RaceEvent e && e.getEventType().equals(CREATE);
    }

    @Override
    public void handleEvent(RaceEvent event) {
        Race entity = mapper.toEntity(event.getRequest());
        service.create(entity);
    }
}
