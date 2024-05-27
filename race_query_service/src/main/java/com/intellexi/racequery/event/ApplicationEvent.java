package com.intellexi.racequery.event;

import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
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
public class ApplicationEvent {

    ApplicationRequestDto request;
    EventType eventType;
}
