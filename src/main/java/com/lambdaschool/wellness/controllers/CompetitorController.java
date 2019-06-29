package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Competition;
import com.lambdaschool.wellness.model.Competitor;
import com.lambdaschool.wellness.repository.CompetitionRepository;
import com.lambdaschool.wellness.repository.CompetitorRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/api/competitor")
@SuppressWarnings("Duplicates")
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
    @GetMapping("/competition/id/{compId}")
    public ResponseEntity<?> getCompetitorsByCompetitionId(@PathVariable long compId) {
        Competition competition = competitionRepo.findCompetitionByCompId(compId);
        Set<Competitor> competitors = competition.getCompetitors();
        return new ResponseEntity<>(competitors, HttpStatus.OK);
    }
    @GetMapping("/public/users/competition/id/{compId}")
    public ResponseEntity<?> getCompetitorsPublicInfoByCompetitionId(@PathVariable long compId) {
        Competition competition = competitionRepo.findCompetitionByCompId(compId);
        Set<Competitor> competitors = competition.getCompetitors();
        String idsArr[] = new String[competitors.size()];
        int i = 0;
        Iterator<Competitor> iterator = competitors.iterator();
        while(iterator.hasNext()) {
            idsArr[i] = iterator.next().getAuth0Id();
            i++;
        }
        //TODO: Create a method to create links easier for our Auth0 Management API
        String findUsersByIdQuery = "user_id:(";
        for(int j = 0; j < idsArr.length; j++) {
            String id = idsArr[j];
            findUsersByIdQuery = findUsersByIdQuery.concat("\"" + id + "\"");
            if (j != idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(" OR ");
            }
            if (j == idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(")");
            }
        }
        Map<String, String> headers = new HashMap<>();
        //Need AUTH0_MANAGEMENT_TOKEN to work with the Auth0 Management API
        headers.put("Authorization", "Bearer " + System.getenv("AUTH0_MANAGEMENT_TOKEN"));

        //Specify what I want from the user and find them by their ids
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fields", "user_metadata,email,user_id");

        queryParams.put("q", findUsersByIdQuery);

        HttpResponse<JsonNode> jsonResponse = Unirest
                .get("https://akshay-gadkari.auth0.com/api/v2/users")
                .headers(headers)
                .queryString(queryParams)
                .asJson();

        return new ResponseEntity<>(jsonResponse.getBody().toString(), HttpStatus.OK);
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

    @PutMapping("/id/{competitorId}")
    public ResponseEntity<?> updateCompetitor(@RequestBody Competitor newCompetitor, @PathVariable long competitorId) {
        Competitor competitor = competitorRepo.findById(competitorId);
        competitor.setHasUploadedReceipt(newCompetitor.isHasUploadedReceipt());
        competitor.setApproved(newCompetitor.isApproved());
        competitor = competitorRepo.save(competitor);
        return new ResponseEntity<>(competitor, HttpStatus.OK);
    }
}
