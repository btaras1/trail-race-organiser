package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ApplicationService {

    ApplicationRepository repository;

    public Application getById(UUID id) {
        return repository.getReferenceById(id);
    }

    public List<Application> getAll() {
        return repository.findAll();
    }
}
