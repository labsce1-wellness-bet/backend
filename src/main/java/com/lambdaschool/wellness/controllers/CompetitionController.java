package com.lambdaschool.wellness.controllers;

import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/group")
@CrossOrigin("http:localhost:3000")

public class CompetitionController
{
    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/all")
    public Iterable<Competition> getAllGroups()
    {
        return competitionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Long id)
    {
        Competition competition = competitionService.findById(id);
        return new ResponseEntity<Competition>(competition, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addGroup(@RequestBody Competition competition)
    {
        Competition newGroup = competitionService.save(competition);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long id)
    {
        competitionService.delete(id);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable long id, @RequestBody Competition competition)
    {
        competitionService.updateGroup(id, competition);


    }

}








