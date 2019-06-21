package com.lambdaschool.wellness.controllers;

import com.auth0.jwk.Jwk;
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
@SuppressWarnings("Duplicates")
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

    @GetMapping("/{compid}")
    public ResponseEntity<?> getByCompid(@PathVariable Long compid) {
        Competition competition = competitionService.findById(compid);
        return new ResponseEntity<Competition>(competition, HttpStatus.OK);
    }

    @PostMapping("/group/{groupid}")
    public Competition addCompetitionToGroup(@PathVariable(value = "groupid") Long groupid,
            @Valid @RequestBody Competition competition) throws Exception {
        // verify and decode jwt
        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);

        // ensuring only the admin can create a competition
        Group adminGroup = groupRepo.findByAdminid(decodedJWT.getSubject());
        if (adminGroup.getAdminid() == decodedJWT.getSubject()) {
            return null;
        }
        return groupRepo.findById(groupid).map(group -> {
            competition.setGroup(group);
            return competitionRepo.save(competition);
        }).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/{compid}")
    public ResponseEntity<?> findById(@PathVariable Long compid) {
        competitionService.delete(compid);
        return new ResponseEntity<String>("Competition deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{compid}")
    public ResponseEntity<?> saveComp(@RequestBody Competition competition, @PathVariable long compid) {
        Competition newComp = new Competition();

        newComp.setCompid(compid);
        newComp.setCompetitionType(competition.getCompetitionType());
        newComp.setMessage(competition.getMessage());
        newComp.setBetAmount(competition.getBetAmount());
        newComp.setStartDate(competition.getStartDate());
        newComp.setEndDate(competition.getEndDate());
        competitionService.save(newComp);

        return new ResponseEntity<>(newComp, HttpStatus.OK);

    }

}
