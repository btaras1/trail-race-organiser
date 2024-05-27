package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.mapper.ApplicationMapper;
import com.intellexi.racequery.mapper.RaceMapper;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import com.intellexi.racequery.service.ApplicationService;
import com.intellexi.racequery.service.RaceService;
import com.intellexi.racequery.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;
    ApplicationService applicationService;
    ApplicationMapper applicationMapper;
    RaceService service;
    RaceMapper mapper;

    @GetMapping(value = "{email}")
    public ResponseEntity<UserDetails> getByEmail(@PathVariable String email) {
        log.info("Received GET request for User, email: {}", email);
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/{id}/applications")
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<List<ApplicationResponseDto>> getAllApplicationsByUserId(@PathVariable UUID id) {
        log.info("Received GET request for all User Applications, user id: {}", id);
        return ResponseEntity.ok(applicationMapper.toResponse(applicationService.findApplicationsByUserId(id)));
    }

    @GetMapping("/{id}/races/unapplied")
    @PreAuthorize("hasAuthority('APPLICANT')")
    public ResponseEntity<List<RaceResponseDto>> getAllUnappliedRaces(@PathVariable  UUID id) {
        log.info("Received GET request for all unapplied Races, user id: {}", id);
        return ResponseEntity.ok(mapper.toResponse(service.getAllUnappliedRaces(id)));
    }
}
