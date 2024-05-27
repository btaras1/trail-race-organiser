package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static com.intellexi.racequery.domain.enums.RaceDistance.MARATHON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class RaceMapperTest {

    static final UUID APPLICATION_ID = UUID.randomUUID();
    static final UUID RACE_ID = UUID.randomUUID();
    static final String RACE_NAME = "5k";

    @Mock
    ApplicationMapper applicationMapper;

    @InjectMocks
    RaceMapper raceMapper;

    @Test
    void toResponse() {
        Race entity = constructEntity();

        RaceResponseDto result = raceMapper.toResponse(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getDistance(), result.getDistance());
        assertNull(entity.getApplications());
    }

    @Test
    void testToResponse() {
        List<Race> entities = List.of(constructEntity());

        List<RaceResponseDto> result = raceMapper.toResponse(entities);

        assertEquals(1, result.size());
        assertEquals(entities.get(0).getId(), result.get(0).getId());
        assertEquals(entities.get(0).getName(), result.get(0).getName());
        assertEquals(entities.get(0).getDistance(), result.get(0).getDistance());
        assertNull(entities.get(0).getApplications());
    }

    @Test
    void toEntity() {
        RaceRequestDto requestDto = constructRequestDto();

        Race result = raceMapper.toEntity(requestDto);

        assertEquals(requestDto.getId(), result.getId());
        assertEquals(requestDto.getName(), result.getName());
        assertEquals(requestDto.getDistance(), result.getDistance());
    }


    private Race constructEntity() {
        return Race.builder()
                .id(RACE_ID)
                .name(RACE_NAME)
                .distance(MARATHON)
                .build();
    }

    private RaceRequestDto constructRequestDto() {
        return RaceRequestDto.builder()
                .id(APPLICATION_ID)
                .name(RACE_NAME)
                .distance(MARATHON)
                .build();
    }
}