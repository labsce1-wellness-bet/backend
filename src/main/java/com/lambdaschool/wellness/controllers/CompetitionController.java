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
        //TODO: Only Admin role can request all competitions - implement once we reach MVP
        return competitionService.findAll();
    }

    @GetMapping("/id/{compId}")
    public ResponseEntity<?> getByCompId(@PathVariable Long compId) {
        //TODO: Only people with JWT can access the competition and must belong to group - implement once we reach MVP
        // if admin of group declines them from entering competition, the user is not allowed to GET competition
        Competition competition = competitionService.findById(compId);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @GetMapping("/group/id/{groupId}")
    public ResponseEntity<?> getCompetitionsByGroupId(@PathVariable(value = "groupId") long groupId) {
        Group group = groupRepo.findByGroupId(groupId);
        return new ResponseEntity<>(group.getCompetitions(), HttpStatus.OK);
    }
    @PostMapping("/group/id/{groupId}")
    public Competition addCompetitionToGroup(@PathVariable(value = "groupId") Long groupId,
            @Valid @RequestBody Competition competition) throws Exception {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);


        // ensuring only the admin of group can create a competition
        Group adminGroup = groupRepo.findByAdminId(decodedJWT.getSubject());
        if (adminGroup.getAdminId() == decodedJWT.getSubject()) {
            return null;
        }
        return groupRepo.findById(groupId).map(group -> {
            competition.setGroup(group);
            return competitionRepo.save(competition);
        }).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/id/{compId}")
    public ResponseEntity<?> findById(@PathVariable Long compId) {
        competitionService.delete(compId);
        return new ResponseEntity<>("Competition deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/id/{compId}")
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
