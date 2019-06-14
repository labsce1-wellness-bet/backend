package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long>
{
    Competition findCompetitionByCompid(Long compid);
}
