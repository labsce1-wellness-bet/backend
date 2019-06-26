package com.lambdaschool.wellness.controllers;

import com.lambdaschool.wellness.model.Competitor;
import com.lambdaschool.wellness.repository.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/api/competitor")
@RestController
public class CompetitorController {
    @Autowired
    private CompetitorRepository competitorRepo;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<Competitor> getAllGroups() throws Exception
    {

        //TODO: Only Admin role can request all competitors - implement once we reach MVP

        return competitorRepo.findAll();
    }


}
