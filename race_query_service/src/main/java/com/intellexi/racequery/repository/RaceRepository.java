package com.intellexi.racequery.repository;

import com.intellexi.racequery.domain.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RaceRepository extends JpaRepository<Race, UUID> {

    @Query("SELECT r FROM Race r WHERE r.id NOT IN (SELECT a.race.id FROM Application a WHERE a.user.id = :userId)")
    List<Race> findRacesUserHasNotApplied(@Param("userId") UUID userId);
}
