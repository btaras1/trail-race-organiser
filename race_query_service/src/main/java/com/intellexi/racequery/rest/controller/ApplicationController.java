package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import com.intellexi.racequery.service.ApplicationService;
import com.intellexi.racequery.service.RaceService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("api/v1/application")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationController {

    ApplicationService service;
    ApplicationMapper mapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<ApplicationResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDto(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDto>> getAll() {
        return ResponseEntity.ok(mapper.toDto(service.getAll()));
    }
}
