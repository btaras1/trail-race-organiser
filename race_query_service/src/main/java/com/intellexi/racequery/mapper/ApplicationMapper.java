package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {
    public ApplicationResponseDto toDto(Application entity) {
        return ApplicationResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getUser().getFirstName())
                .lastName(entity.getUser().getLastName())
                .club(entity.getClub())
                .build();
    }

    public List<ApplicationResponseDto > toDto(List<Application> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
