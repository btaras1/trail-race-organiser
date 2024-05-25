package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RaceMapper {

    ApplicationMapper applicationMapper;
    public RaceResponseDto toDto(Race entity) {
        return RaceResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .distance(entity.getDistance())
                .applications(
                        applicationMapper.toDto(entity.getApplications()))
                .build();
    }

    public List<RaceResponseDto> toDto(List<Race> entites) {
        return entites.stream()
                .map(this::toDto)
                .toList();
    }
}
