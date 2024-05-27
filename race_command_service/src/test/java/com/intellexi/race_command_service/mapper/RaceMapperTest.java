package com.intellexi.race_command_service.mapper;

import com.intellexi.race_command_service.rest.dto.event.RaceEvent;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import com.intellexi.race_command_service.rest.dto.response.RaceResponseDto;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.CREATE;
import static com.intellexi.race_command_service.enums.EventType.DELETE;
import static com.intellexi.race_command_service.enums.RaceDistance.FIVEKM;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
class RaceMapperTest {

    static UUID RACE_ID = UUID.randomUUID();
    static final String RACE_NAME = "5k";

    RaceMapper mapper = new RaceMapper();
    @Test
    void toResponse() {
        RaceRequestDto request = constructRequest();

        RaceResponseDto result = mapper.toResponse(request);

        assertEquals(request.getId(), result.getId());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getDistance(), result.getDistance());
    }

    @Test
    void toEvent() {
        RaceRequestDto request = constructRequest();

        RaceEvent result = mapper.toEvent(request, CREATE);

        assertEquals(request, result.getRequest());
        assertEquals(CREATE, result.getEventType());
    }

    @Test
    void toEvent_deleteEventType() {
        RaceEvent result = mapper.toEvent(RACE_ID);

        assertEquals(RACE_ID, result.getRequest().getId());
        assertEquals(DELETE, result.getEventType());
    }
    private RaceRequestDto constructRequest() {
        return RaceRequestDto.builder()
                .id(RACE_ID)
                .name(RACE_NAME)
                .distance(FIVEKM)
                .build();
    }
}