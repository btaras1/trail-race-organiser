package com.intellexi.racequery.event;

import com.intellexi.racequery.event.enums.EventType;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class RaceEvent {

    RaceRequestDto request;
    EventType eventType;
}
