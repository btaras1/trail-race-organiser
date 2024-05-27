package com.intellexi.racequery.event.strategy.race;

import com.intellexi.racequery.event.RaceEvent;
import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.event.strategy.EventStrategy;
import com.intellexi.racequery.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.intellexi.racequery.event.enums.EventType.DELETE;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceDeleteEventStrategy implements EventStrategy<RaceEvent> {

    RaceService service;

    @Override
    public boolean isSatisfied(Object event) {
        return event instanceof RaceEvent e && e.getEventType().equals(DELETE);
    }

    @Override
    public void handleEvent(RaceEvent event) {
        UUID id = event.getRequest().getId();
        service.delete(id);
    }
}
