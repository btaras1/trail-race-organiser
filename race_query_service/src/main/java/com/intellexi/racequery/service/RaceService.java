package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceService {

    RaceRepository repository;

    public Race getById(UUID id) {
        return repository.getReferenceById(id);
    }

    public List<Race> getAll() {
        return repository.findAll();
    }
}
