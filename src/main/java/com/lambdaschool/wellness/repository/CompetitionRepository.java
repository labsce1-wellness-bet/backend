package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long>
{
    Competition getById(long id);

}
