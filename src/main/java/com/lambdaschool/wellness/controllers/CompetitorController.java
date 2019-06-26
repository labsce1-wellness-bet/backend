package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.model.Competitor;
import com.lambdaschool.wellness.repository.CompetitionRepository;
import com.lambdaschool.wellness.repository.CompetitorRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/competitor")
public class CompetitorController {
    @Autowired
    private CompetitorRepository competitorRepo;

    @Autowired
    private CompetitionRepository competitionRepo;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<Competitor> getAllCompetitors() throws Exception
    {

        //TODO: Only Admin role can request all competitors - implement once we reach MVP

        return competitorRepo.findAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCompetitorById(@PathVariable long id) {
        //TODO: Only people with jwt and belonging to the group can request this route - implement once we reach MVP
        return new ResponseEntity<>(competitorRepo.findById(id), HttpStatus.OK);
    }

    @PostMapping("/competition/id/{compId}")
    public ResponseEntity<?> addCompetitorToCompetition(@PathVariable long compId) throws Exception {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
        //Create and set starting values for competitor
        Competitor competitor = new Competitor();
        competitor.setApproved(false);
        competitor.setAuth0Id(decodedJWT.getSubject());
        competitor.setHasUploadedReceipt(false);

        //Find competition to put competitor in and save it to db
        Competition competition = competitionRepo.findCompetitionByCompId(compId);
        competitor.setCompetition(competition);
        competitor = competitorRepo.save(competitor);

        return new ResponseEntity<>(competitor, HttpStatus.CREATED);
    }


}
