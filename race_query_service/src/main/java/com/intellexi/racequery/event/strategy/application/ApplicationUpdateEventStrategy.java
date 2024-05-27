package com.intellexi.racequery.event.strategy.application;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.event.ApplicationEvent;
import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.event.strategy.EventStrategy;
import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.intellexi.racequery.event.enums.EventType.UPDATE;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationUpdateEventStrategy implements EventStrategy<ApplicationEvent> {

    ApplicationService service;
    ApplicationMapper mapper;

    @Override
    public boolean isSatisfied(Object event) {
        return event instanceof ApplicationEvent e && e.getEventType().equals(UPDATE);
    }

    @Override
    public void handleEvent(ApplicationEvent event) {
        Application entity = mapper.toEntity(event.getRequest());
        service.update(entity);
    }
}
