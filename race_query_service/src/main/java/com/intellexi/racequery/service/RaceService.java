package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.exception.RaceNotFoundException;
import com.intellexi.racequery.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceService {

    RaceRepository repository;

    public Race getById(UUID id) {
        log.info("Getting Race from the DB, id: {}", id);
        return repository.findById(id).orElseThrow(() ->
                new RaceNotFoundException(String.format("Race with the id: %s not found.", id)));
    }

    public List<Race> getAll() {
        log.info("Getting all Races from the DB");
        return repository.findAll();
    }

    public List<Race> getAllUnappliedRaces(UUID id) {
        log.info("Getting all available Races for User from the DB, user id: {}", id);
        return repository.findRacesUserHasNotApplied(id);
    }
    public void create(Race entity) {
        log.info("Creating a new Race in the DB");
        repository.save(entity);
    }

    @Transactional
    public void update(Race updatedEntity) {
        log.info("Updating Race in the DB, id: {}", updatedEntity.getId());
        Race current = getById(updatedEntity.getId());
        current.setName(updatedEntity.getName());
        current.setDistance(updatedEntity.getDistance());
        repository.save(current);
    }

    public void delete(UUID id) {
        log.info("Deleting Race in the DB, id: {}", id);
        repository.deleteById(id);
    }
}
