package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.repository.GroupRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import kong.unirest.HttpResponse;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;


@RestController
@RequestMapping(value = "/api/group")
public class GroupController
{
    @Autowired
    private GroupRepository groupRepo;


    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<Group> getAllGroups() throws Exception
    {
        JWTHelper.decodeJWTWithVerify(request);
        return groupRepo.findAll();
    }

    @GetMapping("/id/{groupId}")
    public ResponseEntity<?> getGroupById(@PathVariable Long groupId) throws Exception
    {
        JWTHelper.decodeJWTWithVerify(request);
        Group group = groupRepo.findByGroupId(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @GetMapping("/id/{groupId}/public/all/user-info")
    public ResponseEntity<?> getAllPublicUserInfoByGroupId(@PathVariable Long groupId) {

        Group group = groupRepo.findByGroupId(groupId);

        String idsArr[] = new String[group.getAuth0Ids().size()];
        idsArr = group.getAuth0Ids().toArray(idsArr);
        //TODO: Create a method to create links easier for our Auth0 Management API
        String findUsersByIdQuery = "user_id:(";
        for(int i = 0; i < idsArr.length; i++) {
            String id = idsArr[i];
            findUsersByIdQuery = findUsersByIdQuery.concat("\"" + id + "\"");
            if (i != idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(" OR ");
            }
            if (i == idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(")");
            }
        }
        //TODO: Have to figure out how where to place Unirest config property, since its a configuration for every file
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        Map<String, String> headers = new HashMap<>();
        //Need AUTH0_MANAGEMENT_TOKEN to work with the Auth0 Management API
        headers.put("Authorization", "Bearer " + System.getenv("AUTH0_MANAGEMENT_TOKEN"));

        //Specify what I want from the user and find them by their ids
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fields", "user_metadata,email");
        queryParams.put("q", findUsersByIdQuery);

        HttpResponse<JsonNode> jsonResponse = Unirest
                .get("https://akshay-gadkari.auth0.com/api/v2/users")
                .headers(headers)
                .queryString(queryParams)
                .asJson();

        return new ResponseEntity<>(jsonResponse.getBody().toString(), HttpStatus.OK);
    }

    @GetMapping("/all/admin")
    public Group getGroupsByAdminId() throws Exception {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
        return groupRepo.findAllByAdminId(decodedJWT.getSubject());
        }


    private String createSecretCode() {
        int length = 7;
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));

        }
        return builder.toString();
    }
    @PostMapping("")
    public ResponseEntity<?> addNewGroup(@Valid @RequestBody Group newGroup) throws Exception
    {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
        //we set our starting values
        newGroup.setAdminId(decodedJWT.getSubject());
        newGroup.setSecretCode(createSecretCode());
        Set<String> newSet = new HashSet<>();
        newSet.add(decodedJWT.getSubject());
        newGroup.setAuth0Ids(newSet);
        //Save the new group into the database
        newGroup.getAuth0Ids().add(decodedJWT.getSubject());
        newGroup = groupRepo.save(newGroup);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/groupId").buildAndExpand(newGroup.getGroupId()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newGroup,HttpStatus.CREATED);
    }
    @PutMapping("/join-group/{secretCode}")
    public Group addUserToGroup(@PathVariable String secretCode) throws Exception {
        //verify and decode jwt
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);

        Group group = groupRepo.findBySecretCode(secretCode);

        group.getAuth0Ids().add(decodedJWT.getSubject());
        group = groupRepo.save(group);
        return group;
    }


    @DeleteMapping("/id/{groupId}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long groupId)
    {
        //TODO: Delete Group by ID, needs jwt protection and admin role on Auth0 - implement after MVP is finished
        Group group = groupRepo.findByGroupId(groupId);
        groupRepo.delete(group);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

    }
    @DeleteMapping("/id/{groupId}/admin")
    public String deleteGroupAsGroupAdmin(@PathVariable Long groupId) throws Exception
    {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
        String auth0Id = decodedJWT.getSubject();

        Group group = groupRepo.findByGroupId(groupId);

        if(auth0Id.equals(group.getAdminId())) {
            String groupName = group.getGroupName();
            groupRepo.delete(group);
            return groupName;
        }
        return null;
    }
    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@RequestBody Group group, @PathVariable long groupId)
    {
        Group newGroup = new Group();
        //Group newGroup = groupService.findById(groupid);

        newGroup.setGroupId(groupId);
        newGroup.setGroupName(group.getGroupName());
        newGroup.setAdminId(group.getAdminId());
        newGroup.setSecretCode(group.getSecretCode());
        groupRepo.save(newGroup);

        return new ResponseEntity<>(newGroup,HttpStatus.OK);


    }

}








