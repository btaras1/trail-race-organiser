package com.intellexi.race_command_service.service;

import com.intellexi.race_command_service.exception.ApplicationBadRequestException;
import com.intellexi.race_command_service.mapper.ApplicationMapper;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.CREATE;
import static com.intellexi.race_command_service.enums.EventType.UPDATE;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = PRIVATE)
class ApplicationServiceTest {

    static UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";

    @Captor
    ArgumentCaptor<ApplicationRequestDto> requestCaptor;
    @Mock
    ApplicationMapper mapper;
    @Mock
    EventService eventService;

    @InjectMocks
    ApplicationService service;

    @Test
    void create() {
        service.create(constructRequest());

        verify(mapper, times(1))
                .toEvent(requestCaptor.capture(), eq(CREATE));

        verify(eventService, times(1))
                .sendApplicationEvent(any());

        assertNotNull(requestCaptor.getValue().getId());
    }

    @Test
    void update() {
        ApplicationRequestDto request = constructRequest();
        service.update(request);

        verify(mapper, times(1))
                .toEvent(request, UPDATE);

        verify(eventService, times(1))
                .sendApplicationEvent(any());
    }

    @Test
    void delete() {
        service.delete(APPLICATION_ID);

        verify(mapper, times(1))
                .toEvent(APPLICATION_ID);

        verify(eventService, times(1))
                .sendApplicationEvent(any());
    }

    @Test
    void create_emptyRequest_throwsException() {
        assertThrows(ApplicationBadRequestException.class,
                () -> service.create(ApplicationRequestDto.builder().build()));
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