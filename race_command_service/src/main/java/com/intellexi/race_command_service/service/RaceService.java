package com.intellexi.race_command_service.service;

import com.intellexi.race_command_service.exception.ApplicationBadRequestException;
import com.intellexi.race_command_service.mapper.RaceMapper;
import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.intellexi.race_command_service.enums.EventType.CREATE;
import static com.intellexi.race_command_service.enums.EventType.UPDATE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RaceService {

    RaceMapper mapper;
    EventService eventService;
    public RaceRequestDto create(RaceRequestDto request) {
        validate(request);
        request.setId(createId(request));
        eventService.sendRaceEvent(mapper.toEvent(request, CREATE));
        return request;
    }

    private UUID createId(RaceRequestDto request) {
        return request.getId() != null ? request.getId()
                : UUID.randomUUID();
    }

    public RaceRequestDto update(RaceRequestDto request) {
        validate(request);
        eventService.sendRaceEvent(mapper.toEvent(request, UPDATE));
        return request;
    }

    public void delete(UUID id) {
        eventService.sendRaceEvent(mapper.toEvent(id));
    }

    private void validate(RaceRequestDto request) {
        log.info("Validating request...");
        if(request.getName() == null
                || request.getDistance() == null) {
            throw new ApplicationBadRequestException("Name and Distance cannot be null!");
        }
    }
}
