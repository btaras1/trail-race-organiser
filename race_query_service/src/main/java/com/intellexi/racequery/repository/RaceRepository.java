package com.intellexi.racequery.repository;

import com.intellexi.racequery.domain.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RaceRepository extends JpaRepository<Race, UUID> {
}
