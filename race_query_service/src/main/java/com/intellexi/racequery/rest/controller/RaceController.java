package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import com.intellexi.racequery.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("api/v1/race")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceController {

    RaceService service;
    RaceMapper mapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<RaceResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDto(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<RaceResponseDto>> getAll() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }
}
