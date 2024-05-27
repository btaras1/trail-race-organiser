package com.intellexi.racequery.domain;

import com.intellexi.racequery.domain.enums.RaceDistance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@Table(name = "race")
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaceDistance distance;

    @OneToMany(mappedBy = "race", fetch = EAGER)
    private List<Application> applications;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
