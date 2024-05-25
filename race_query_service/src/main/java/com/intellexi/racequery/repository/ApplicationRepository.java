package com.intellexi.racequery.repository;

import com.intellexi.racequery.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
}
