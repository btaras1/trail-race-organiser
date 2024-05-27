package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.service.RaceService;
import com.intellexi.racequery.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ApplicationMapperTest {

    static final UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final UUID USER_ID = UUID.randomUUID();
    static final String CLUB = "CLUB_NAME";
    static final String FIRST_NAME = "FIRST_NAME";
    static final String LAST_NAME = "LAST_NAME";
    @Mock
    UserService userService;
    @Mock
    RaceService raceService;

    @InjectMocks
    ApplicationMapper applicationMapper;
    @Test
    void toResponse() {
        Application entity = constructEntity();
        ApplicationResponseDto result =
                applicationMapper.toResponse(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getUser().getFirstName(), result.getFirstName());
        assertEquals(entity.getUser().getLastName(), result.getLastName());
        assertEquals(entity.getClub(), result.getClub());
    }

    @Test
    void toResponseList() {
        List<Application> entities = List.of(constructEntity());

        List<ApplicationResponseDto> result =
                applicationMapper.toResponse(entities);

        assertEquals(1, result.size());
        assertEquals(entities.get(0).getId(), result.get(0).getId());
        assertEquals(entities.get(0).getUser().getFirstName(), result.get(0).getFirstName());
        assertEquals(entities.get(0).getUser().getLastName(), result.get(0).getLastName());
        assertEquals(entities.get(0).getClub(), result.get(0).getClub());
    }

    @Test
    void toEntity() {
        ApplicationRequestDto request = constructRequestDto();
        User entityUser = constructUser();
        Race entityRace = constructRace();
        given(userService.findById(request.getUserId())).willReturn(entityUser);
        given(raceService.getById(request.getRaceId())).willReturn(entityRace);

        Application result = applicationMapper.toEntity(request);

        assertEquals(request.getId(), result.getId());
        assertEquals(request.getClub(), result.getClub());
        assertEquals(entityUser, result.getUser());
        assertEquals(entityRace, result.getRace());
    }

    private Application constructEntity() {
        return Application.builder()
                .id(APPLICATION_ID)
                .user(constructUser())
                .race(constructRace())
                .club(CLUB)
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

    private ApplicationRequestDto constructRequestDto() {
        return ApplicationRequestDto.builder()
                .id(APPLICATION_ID)
                .raceId(RACE_ID)
                .userId(USER_ID)
                .club(CLUB)
                .build();
    }
}