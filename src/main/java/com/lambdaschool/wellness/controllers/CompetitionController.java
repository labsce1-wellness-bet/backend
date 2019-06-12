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


@RestController
@RequestMapping(value = "/api/groups")
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

    @GetMapping("/{groupid}")
    public ResponseEntity<?> getGroupById(@PathVariable Long groupid)
    {
        Competition competition = competitionService.findById(groupid);
        return new ResponseEntity<Competition>(competition, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addNewGroup(@Valid @RequestBody Competition newGroup) throws URISyntaxException
    {
        newGroup = competitionService.save(newGroup);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/groupid").buildAndExpand(newGroup.getGroupid()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }



    @DeleteMapping("/{groupid}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long groupid)
    {
        competitionService.delete(groupid);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{groupid}")
    public ResponseEntity<?> saveGroup(@RequestBody Competition competition, @PathVariable long groupid)
    {
        Competition newGroup = new Competition();

        newGroup.setGroupid(groupid);
        newGroup.setGroup_name(competition.getGroup_name());
        newGroup.setActivity(competition.getActivity());
        newGroup.setGoal(competition.getGoal());
        newGroup.setAdmin(competition.getAdmin());
        newGroup.setBet_amount(competition.getBet_amount());
        newGroup.setMessage(competition.getMessage());
        newGroup.setStart_date(competition.getStart_date());
        newGroup.setEnd_date(competition.getEnd_date());
        newGroup.setInvite_code(competition.getInvite_code());
        competitionService.save(newGroup);

        return new ResponseEntity<>(newGroup,HttpStatus.OK);


    }

}








