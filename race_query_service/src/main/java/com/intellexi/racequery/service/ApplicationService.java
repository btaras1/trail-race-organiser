package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.exception.ApplicationNotFoundException;
import com.intellexi.racequery.repository.ApplicationRepository;
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
public class ApplicationService {

    ApplicationRepository repository;

    public Application getById(UUID id) {
        log.info("Getting Application from the DB, id: {}", id);
        return repository.findById(id).orElseThrow(() ->
                new ApplicationNotFoundException(String.format("Application with the id: %s not found.", id)));
    }

    public List<Application> getAll() {
        log.info("Getting all Applications from the DB");
        return repository.findAll();
    }

    public void create(Application entity) {
        log.info("Creating a new Application in the DB");
        repository.save(entity);
    }

    @Transactional
    public void update(Application updatedEntity) {
        log.info("Updating Application in the DB, id: {}", updatedEntity.getId());
        Application current = getById(updatedEntity.getId());
        current.setClub(updatedEntity.getClub());
        repository.save(current);
    }

    public void delete(UUID id) {
        log.info("Deleting Application in the DB, id: {}", id);
        repository.deleteById(id);
    }

    public List<Application> findApplicationsByUserId(UUID id) {
        log.info("Getting all User Applications from the DB, user id: {}", id);
        return repository.findByUserId(id);
    }
}
