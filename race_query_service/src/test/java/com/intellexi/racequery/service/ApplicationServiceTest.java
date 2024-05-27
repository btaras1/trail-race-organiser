package com.intellexi.racequery.service;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.exception.ApplicationNotFoundException;
import com.intellexi.racequery.exception.UserNotFoundException;
import com.intellexi.racequery.repository.ApplicationRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    static final UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";
    static final String UPDATE_CLUB = "CLUB_UPDATED_NAME";
    static final String FIRST_NAME = "FIRST_NAME";
    static final String LAST_NAME = "LAST_NAME";

    @Captor
    ArgumentCaptor<Application> argumentCaptor;
    @Mock
    ApplicationRepository repository;

    @InjectMocks
    ApplicationService service;
    @Test
    void getById() {
        Application entity = constructEntity(CLUB);

        given(repository.findById(APPLICATION_ID)).willReturn(Optional.of(entity));

        Application result = service.getById(APPLICATION_ID);

        assertEquals(entity, result);
    }

    @Test
    void getById_notFound_throwsException() {
        given(repository.findById(APPLICATION_ID)).willReturn(Optional.empty());

        assertThrows(ApplicationNotFoundException.class,
                () -> service.getById(APPLICATION_ID));
    }

    @Test
    void getAll() {
        List<Application> entities = List.of(constructEntity(CLUB));

        given(repository.findAll()).willReturn(entities);

        List<Application>  result = service.getAll();

        assertEquals(entities, result);
    }

    @Test
    void create() {
        Application entity = constructEntity(CLUB);

        service.create(entity);

        verify(repository, times(1)).save(entity);
    }

    @Test
    void update() {
        Application entity = constructEntity(CLUB);
        Application updatedEntity = constructEntity(UPDATE_CLUB);

        given(repository.findById(APPLICATION_ID)).willReturn(Optional.of(entity));

        service.update(updatedEntity);

        verify(repository, times(1)).save(argumentCaptor.capture());

        assertEquals(updatedEntity, argumentCaptor.getValue());
    }

    @Test
    void delete() {
        service.delete(APPLICATION_ID);

        verify(repository, times(1)).deleteById(APPLICATION_ID);
    }

    @Test
    void findApplicationsByUserId() {
        List<Application> entities = List.of(constructEntity(CLUB));

        given(repository.findByUserId(USER_ID)).willReturn(entities);

        List<Application>  result = service.findApplicationsByUserId(USER_ID);

        assertEquals(entities, result);
    }

    private Application constructEntity(String club) {
        return Application.builder()
                .id(APPLICATION_ID)
                .user(constructUser())
                .race(constructRace())
                .club(club)
                .build();
    }

    private User constructUser() {
        return User.builder()
                .id(USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }

    private Race constructRace() {
        return Race.builder()
                .id(RACE_ID)
                .build();
    }
}