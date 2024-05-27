package com.intellexi.race_command_service.rest.controller;

import com.intellexi.race_command_service.mapper.RaceMapper;
import com.intellexi.race_command_service.rest.dto.request.RaceRequestDto;
import com.intellexi.race_command_service.rest.dto.response.RaceResponseDto;
import com.intellexi.race_command_service.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("api/v1/races")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceController {

    RaceService service;
    RaceMapper mapper;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<RaceResponseDto> create(@RequestBody RaceRequestDto request) {
        log.info("Got POST Race request: {}", request);
        return ResponseEntity.ok(mapper.toResponse(service.create(request)));
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<RaceResponseDto> update(@RequestBody RaceRequestDto request) {
        log.info("Got PATCH Race request: {}", request);
        return ResponseEntity.ok(mapper.toResponse(service.update(request)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        log.info("Got DELETE Race request: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
