package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/applications")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationController {

    ApplicationService service;
    ApplicationMapper mapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<ApplicationResponseDto> getById(@PathVariable UUID id) {
        log.info("Received GET request for Application, id: {}", id);
        return ResponseEntity.ok(mapper.toResponse(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDto>> getAll() {
        log.info("Received GET request for all Applications");
        return ResponseEntity.ok(mapper.toResponse(service.getAll()));
    }
}
