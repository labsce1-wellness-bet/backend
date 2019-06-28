package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
    Competitor findById(long competitorId);
}
