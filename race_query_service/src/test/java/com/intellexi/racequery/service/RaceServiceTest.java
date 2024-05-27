package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.domain.enums.RaceDistance;
import com.intellexi.racequery.exception.ApplicationNotFoundException;
import com.intellexi.racequery.exception.RaceNotFoundException;
import com.intellexi.racequery.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.intellexi.racequery.domain.enums.RaceDistance.FIVEKM;
import static com.intellexi.racequery.domain.enums.RaceDistance.MARATHON;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {

    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();

    static final String RACE_NAME = "RACE_NAME";
    static final RaceDistance RACE_DISTANCE = FIVEKM;
    static final RaceDistance NEW_RACE_DISTANCE = MARATHON;

    @Captor
    ArgumentCaptor<Race> argumentCaptor;
    @Mock
    RaceRepository repository;
    @InjectMocks
    RaceService service;

    @Test
    void getById() {
        Race entity = constructRace(RACE_DISTANCE);

        given(repository.findById(RACE_ID)).willReturn(Optional.of(entity));

        Race result = service.getById(RACE_ID);

        assertEquals(entity, result);
    }

    @Test
    void getById_notFound_throwsException() {
        given(repository.findById(RACE_ID)).willReturn(Optional.empty());

        assertThrows(RaceNotFoundException.class,
                () -> service.getById(RACE_ID));
    }

    @Test
    void getAll() {
        List<Race> entities = List.of(constructRace(RACE_DISTANCE));

        given(repository.findAll()).willReturn(entities);

        List<Race> result = service.getAll();

        assertEquals(entities, result);
    }

    @Test
    void getAllUnappliedRaces() {
        List<Race> entities = List.of(constructRace(RACE_DISTANCE));

        given(repository.findRacesUserHasNotApplied(USER_ID)).willReturn(entities);

        List<Race> result = service.getAllUnappliedRaces(USER_ID);

        assertEquals(entities, result);
    }

    @Test
    void create() {
        Race entity = constructRace(RACE_DISTANCE);

        service.create(entity);

        verify(repository, times(1)).save(entity);
    }

    @Test
    void update() {
        Race entity = constructRace(RACE_DISTANCE);
        Race updatedEntity = constructRace(NEW_RACE_DISTANCE);

        given(repository.findById(RACE_ID)).willReturn(Optional.of(entity));

        service.update(updatedEntity);

        verify(repository, times(1)).save(argumentCaptor.capture());

        assertEquals(updatedEntity, argumentCaptor.getValue());
    }

    @Test
    void delete() {
        service.delete(RACE_ID);

        verify(repository, times(1)).deleteById(RACE_ID);
    }

    private Race constructRace(RaceDistance distance) {
        return Race.builder()
                .id(RACE_ID)
                .name(RACE_NAME)
                .distance(distance)
                .build();
    }
}