package com.intellexi.race_command_service.service;

import com.intellexi.race_command_service.exception.ApplicationBadRequestException;
import com.intellexi.race_command_service.mapper.ApplicationMapper;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.CREATE;
import static com.intellexi.race_command_service.enums.EventType.UPDATE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApplicationService {


    ApplicationMapper mapper;
    EventService eventService;
    public void create(ApplicationRequestDto request) {
        validate(request);
        request.setId(createId(request));
        eventService.sendApplicationEvent(mapper.toEvent(request, CREATE));
    }

    private UUID createId(ApplicationRequestDto request) {
        return request.getId() != null ? request.getId()
                : UUID.randomUUID();
    }

    public void update(ApplicationRequestDto request) {
        validate(request);
        eventService.sendApplicationEvent(mapper.toEvent(request, UPDATE));
    }

    public void delete(UUID id) {
        eventService.sendApplicationEvent(mapper.toEvent(id));
    }

    private void validate(ApplicationRequestDto request) {
        log.info("Validating request...");
        if(request.getRace_id() == null
                || request.getUser_id() == null) {
            throw new ApplicationBadRequestException("User and Race cannot be null!");
        }
    }
}
