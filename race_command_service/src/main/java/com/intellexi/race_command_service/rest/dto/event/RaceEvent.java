package com.intellexi.race_command_service.rest.dto.event;

import com.intellexi.race_command_service.enums.EventType;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
public class RaceEvent {

    RaceRequestDto request;
    EventType eventType;
}
