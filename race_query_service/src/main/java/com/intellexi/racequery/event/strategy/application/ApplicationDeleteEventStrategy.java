package com.intellexi.racequery.event.strategy.application;

import com.intellexi.racequery.event.ApplicationEvent;
import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.event.strategy.EventStrategy;
import com.intellexi.racequery.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.intellexi.racequery.event.enums.EventType.DELETE;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationDeleteEventStrategy implements EventStrategy<ApplicationEvent> {

    ApplicationService service;

    @Override
    public boolean isSatisfied(Object event) {
        return event instanceof ApplicationEvent e && e.getEventType().equals(DELETE);
    }

    @Override
    public void handleEvent(ApplicationEvent event) {
        UUID id = event.getRequest().getId();
        service.delete(id);
    }
}
