package com.lambdaschool.wellness.controllers;

import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/api/competition")
@RestController
public class CompetitionController
{
    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/all")
    public Iterable<Competition> getAllComp()
    {
        return competitionService.findAll();
    }

    @GetMapping("/{compid}")
    public ResponseEntity<?> getByCompid(@PathVariable Long compid)
    {
        Competition competition = competitionService.findById(compid);
        return new ResponseEntity<Competition>(competition, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> addNewComp(@Valid @RequestBody Competition newComp) throws URISyntaxException
    {
        newComp = competitionService.save(newComp);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/compid").buildAndExpand(newComp.getCompid()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{compid}")
    public ResponseEntity<?> findById(@PathVariable Long compid)
    {
        competitionService.delete(compid);
        return new ResponseEntity<String>("Competition deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{compid}")
    public ResponseEntity<?> saveComp(@RequestBody Competition competition, @PathVariable long compid)
    {
        Competition newComp = new Competition();

        newComp.setCompid(compid);
        newComp.setCompetitionType(competition.getCompetitionType());
        newComp.setMessage(competition.getMessage());
        newComp.setBetAmount(competition.getBetAmount());
        newComp.setStartDate(competition.getStartDate());
        newComp.setEndDate(competition.getEndDate());
        competitionService.save(newComp);

        return new ResponseEntity<>(newComp,HttpStatus.OK);


    }

}
