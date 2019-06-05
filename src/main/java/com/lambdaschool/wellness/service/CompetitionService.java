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

    public Competition findById(Long id){

        return competitionRepository.getById(id);
    }

    public Competition save(Competition competition)
    {
        return competitionRepository.save(competition);
    }

    public void delete(Long id)
    {
        Competition competition = findById(id);
        competitionRepository.delete(competition);
    }

    public void updateGroup(long id, Competition competition)
    {
        competitionRepository.save(competition);

    }

}


