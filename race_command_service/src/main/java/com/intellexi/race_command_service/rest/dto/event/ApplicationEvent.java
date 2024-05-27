package com.intellexi.race_command_service.rest.dto.event;

import com.intellexi.race_command_service.enums.EventType;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
@FieldDefaults(level = PRIVATE)
public class ApplicationEvent {

    ApplicationRequestDto request;
    EventType eventType;
}
