package com.lambdaschool.wellness.service;

import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService
{
    @Autowired
    private CompetitionRepository competitionRepository;

    public Iterable<Competition>findAll(){

        return competitionRepository.findAll();
    }

    public Competition findById(Long compid){

        return competitionRepository.findCompetitionByCompid(compid);
    }

    public Competition save(Competition newComp)
    {
        return competitionRepository.save(newComp);
    }

    public void delete(Long compid)
    {
        Competition competition = findById(compid);
        competitionRepository.delete(competition);
    }

    public void updateComp(Long compid, Competition competition)
    {
        competitionRepository.save(competition);

    }

}
