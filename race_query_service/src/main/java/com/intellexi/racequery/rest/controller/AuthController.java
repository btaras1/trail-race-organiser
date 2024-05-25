package com.intellexi.racequery.rest.controller;

import com.intellexi.racequery.rest.dto.request.AuthRequestDto;
import com.intellexi.racequery.rest.dto.response.AuthResponseDto;
import com.intellexi.racequery.security.JwtService;
import com.intellexi.racequery.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequestDto));
    }
}
