package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.exception.UserNotFoundException;
import com.intellexi.racequery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserService {

    UserRepository repository;

    public User findByEmail(String username) {
        log.info("Getting User by email in the DB, email: {}", username);
        return repository.findByEmail(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with email %s not found.", username)));
    }

    public User findById(UUID id) {
        log.info("Getting User by id in the DB, id: {}", id);
        return repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: %s not found.", id)));
    }
}
