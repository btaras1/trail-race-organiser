package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import com.intellexi.racequery.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "{id}")
    public ResponseEntity<RaceResponseDto> getById(@PathVariable UUID id) {
        log.info("Received GET request for Race, id: {}", id);
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<RaceResponseDto>> getAll() {
        log.info("Received GET request for all Races");
        return ResponseEntity.ok(mapper.toResponse(service.getAll()));
    }
}
