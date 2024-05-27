package com.intellexi.race_command_service.mapper;

import com.intellexi.race_command_service.enums.EventType;
import com.intellexi.race_command_service.rest.dto.event.RaceEvent;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import com.intellexi.race_command_service.rest.dto.response.RaceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.DELETE;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RaceMapper {

    public RaceResponseDto toResponse(RaceRequestDto request) {
        return RaceResponseDto.builder()
                .id(request.getId())
                .name(request.getName())
                .distance(request.getDistance())
                .build();
    }

    public RaceEvent toEvent(RaceRequestDto request, EventType eventType) {
        return RaceEvent.builder()
                .request(request)
                .eventType(eventType)
                .build();
    }

    public RaceEvent toEvent(UUID id) {
        return RaceEvent.builder()
                .request(RaceRequestDto.builder()
                        .id(id)
                        .build())
                .eventType(DELETE)
                .build();
    }
}
