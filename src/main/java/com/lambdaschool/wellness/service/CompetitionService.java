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

        return competitionRepository.findByGroupid(id);
    }

    public Competition save(Competition newGroup)
    {
        return competitionRepository.save(newGroup);
    }

    public void delete(Long groupid)
    {
        Competition competition = findById(groupid);
        competitionRepository.delete(competition);
    }

    public void updateGroup(Long groupid, Competition competition)
    {
        competitionRepository.save(competition);

    }

}


