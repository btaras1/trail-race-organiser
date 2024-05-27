package com.intellexi.race_command_service.mapper;

import com.intellexi.race_command_service.rest.dto.event.ApplicationEvent;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.CREATE;
import static com.intellexi.race_command_service.enums.EventType.DELETE;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
class ApplicationMapperTest {

    static UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";
    ApplicationMapper mapper = new ApplicationMapper();
    @Test
    void toEvent() {
        ApplicationRequestDto request = constructRequest();

        ApplicationEvent result = mapper.toEvent(request, CREATE);

        assertEquals(request, result.getRequest());
        assertEquals(CREATE, result.getEventType());
    }

    @Test
    void toEvent_deleteEventType() {
        ApplicationEvent result = mapper.toEvent(APPLICATION_ID);

        assertEquals(APPLICATION_ID, result.getRequest().getId());
        assertEquals(DELETE, result.getEventType());
    }

    private ApplicationRequestDto constructRequest() {
        return ApplicationRequestDto.builder()
                .id(APPLICATION_ID)
                .club(CLUB)
                .race_id(RACE_ID)
                .user_id(USER_ID)
                .build();
    }
}