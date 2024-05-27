package com.intellexi.race_command_service.mapper;

import com.intellexi.race_command_service.enums.EventType;
import com.intellexi.race_command_service.rest.dto.event.ApplicationEvent;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.DELETE;

@Component
public class ApplicationMapper {

    public ApplicationEvent toEvent(ApplicationRequestDto request, EventType eventType) {
        return ApplicationEvent.builder()
                .request(request)
                .eventType(eventType)
                .build();
    }

    public ApplicationEvent toEvent(UUID id) {
        return ApplicationEvent.builder()
                .request(ApplicationRequestDto.builder()
                        .id(id)
                        .build())
                .eventType(DELETE)
                .build();
    }
}
