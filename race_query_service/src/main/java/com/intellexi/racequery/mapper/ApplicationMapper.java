package com.intellexi.racequery.mapper;

import com.intellexi.racequery.domain.Application;
import com.intellexi.racequery.domain.Race;
import com.intellexi.racequery.domain.User;
import com.intellexi.racequery.rest.dto.request.ApplicationRequestDto;
import com.intellexi.racequery.rest.dto.response.ApplicationResponseDto;
import com.intellexi.racequery.service.RaceService;
import com.intellexi.racequery.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {

    UserService userService;
    RaceService raceService;

    public ApplicationResponseDto toResponse(Application entity) {
        return ApplicationResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getUser().getFirstName())
                .lastName(entity.getUser().getLastName())
                .club(entity.getClub())
                .build();
    }

    public List<ApplicationResponseDto > toResponse(List<Application> entities) {
        return entities.stream()
                .map(this::toResponse)
                .toList();
    }

    public Application toEntity(ApplicationRequestDto request) {
        User user = userService.findById(request.getUserId());
        Race race = raceService.getById(request.getRaceId());

        return Application.builder()
                .id(request.getId())
                .user(user)
                .race(race)
                .club(request.getClub())
                .build();
    }
}
