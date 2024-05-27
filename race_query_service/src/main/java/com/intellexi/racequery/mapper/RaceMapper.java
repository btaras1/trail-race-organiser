package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.domain.enums.RaceDistance;
import com.intellexi.racequery.rest.dto.request.RaceRequestDto;
import com.intellexi.racequery.rest.dto.response.RaceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RaceMapper {

    ApplicationMapper applicationMapper;
    public RaceResponseDto toResponse(Race entity) {
        return RaceResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .distance(entity.getDistance())
                .applications(
                        entity.getApplications() != null ?
                        applicationMapper.toResponse(entity.getApplications())
                        : null)
                .build();
    }

    public List<RaceResponseDto> toResponse(List<Race> entites) {
        return entites.stream()
                .map(this::toResponse)
                .toList();
    }

    public Race toEntity(RaceRequestDto requestDto) {
        return Race.builder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .distance(requestDto.getDistance())
                .build();
    }
}
