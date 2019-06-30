package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.repository.GroupRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import com.lambdaschool.wellness.service.Auth0.ManagementAPIHelper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


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
        //TODO: Only Admin role can request all groups - implement once we reach MVP
        return groupRepo.findAll();
    }
    @GetMapping("/all/auth0id")
    public ResponseEntity<?> getGroupsByAuth0Id() throws Exception {
           DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
           List<Group> groups = groupRepo.findByAuth0Ids(decodedJWT.getSubject());

           return new ResponseEntity<>(groups, HttpStatus.OK);
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

        HttpResponse<JsonNode> jsonResponse = ManagementAPIHelper
                .getUsersInfoFromAuth0(group.getAuth0Ids(), "user_metadata,email");

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
        newGroup.getAuth0Ids().add(decodedJWT.getSubject());
        //Save the new group into the database
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
    @PutMapping("/id/{groupId}")
    public ResponseEntity<?> updateGroup(@RequestBody Group newGroup, @PathVariable long groupId)
    {
        Group group = groupRepo.findByGroupId(groupId);
        group.setGroupName(newGroup.getGroupName());
        group = groupRepo.save(group);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}








