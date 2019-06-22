package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.repository.CompetitionRepository;
import com.lambdaschool.wellness.repository.GroupRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import com.lambdaschool.wellness.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "/api/competition")
@RestController
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private CompetitionRepository competitionRepo;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<Competition> getAllComp() {
        return competitionService.findAll();
    }

    @GetMapping("/{compId}")
    public ResponseEntity<?> getByCompId(@PathVariable Long compId) {
        Competition competition = competitionService.findById(compId);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @PostMapping("/group/{groupId}")
    public Competition addCompetitionToGroup(@PathVariable(value = "groupId") Long groupid,
            @Valid @RequestBody Competition competition) throws Exception {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);


        // ensuring only the admin can create a competition
        Group adminGroup = groupRepo.findByAdminId(decodedJWT.getSubject());
        if (adminGroup.getAdminId() == decodedJWT.getSubject()) {
            return null;
        }
        return groupRepo.findById(groupid).map(group -> {
            competition.setGroup(group);
            return competitionRepo.save(competition);
        }).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<?> findById(@PathVariable Long compId) {
        competitionService.delete(compId);
        return new ResponseEntity<>("Competition deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{compId}")
    public ResponseEntity<?> saveComp(@RequestBody Competition competition, @PathVariable long compId) {
        Competition newComp = new Competition();

        newComp.setCompId(compId);
        newComp.setCompetitionType(competition.getCompetitionType());
        newComp.setMessage(competition.getMessage());
        newComp.setBetAmount(competition.getBetAmount());
        newComp.setStartDate(competition.getStartDate());
        newComp.setEndDate(competition.getEndDate());
        competitionService.save(newComp);

        return new ResponseEntity<>(newComp, HttpStatus.OK);

    }

}
