package com.intellexi.race_command_service.rest.controller;

import com.intellexi.race_command_service.rest.dto.request.ApplicationRequestDto;
import com.intellexi.race_command_service.service.ApplicationService;
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
@RequestMapping("api/v1/applications")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationController {

    ApplicationService service;

    @PostMapping
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<?> create(@RequestBody ApplicationRequestDto request) {
        log.info("Got POST Application request: {}", request);
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<?> update(@RequestBody ApplicationRequestDto request) {
        log.info("Got PUT Application request: {}", request);
        service.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        log.info("Got DELETE Application request: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
