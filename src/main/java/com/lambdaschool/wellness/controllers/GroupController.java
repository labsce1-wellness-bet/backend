package com.lambdaschool.wellness.controllers;

import com.auth0.jwk.Jwk;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.repository.GroupRepository;
import com.lambdaschool.wellness.repository.UserRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Random;


@RestController
@RequestMapping(value = "/api/group")
@SuppressWarnings("Duplicates")
public class GroupController
{
    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<Group> getAllGroups()
    {
        return groupRepo.findAll();
    }

    @GetMapping("/{groupid}")
    public ResponseEntity<?> getGroupById(@PathVariable Long groupid)
    {
        Group group = groupRepo.findByGroupid(groupid);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }


    private String createInviteCode() {
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
        //verify and decode jwt
        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);

        //we set our starting values
        newGroup.setAdminid(decodedJWT.getSubject());
        User currentUser = userRepo.findByAuth0id(decodedJWT.getSubject());
        System.out.println(newGroup);
        //Save the new group into the database

        newGroup = groupRepo.save(new Group(
                newGroup.getGroup_name(),
                createInviteCode(),
                decodedJWT.getSubject(),
                currentUser
        ));
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/groupid").buildAndExpand(newGroup.getGroupid()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newGroup,HttpStatus.CREATED);
    }
    @PutMapping("/join-group/{groupid}")
    public Group addUserToGroup(@PathVariable Long groupid, @RequestBody HashMap<String, String> data) throws Exception {
        //verify and decode jwt
        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);
        Group group = groupRepo.findByGroupid(groupid);

        if(group.getInvite_code().equals(data.get("invite_code"))) {
            User currentUser = userRepo.findByAuth0id(decodedJWT.getSubject());
            group.getUsers().add(currentUser);
            group = groupRepo.save(group);

            return group;
        } else {
            return null;
        }
    }


    @DeleteMapping("/{groupid}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long groupid)
    {
        Group group = groupRepo.findByGroupid(groupid);
        groupRepo.delete(group);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{groupid}")
    public ResponseEntity<?> saveGroup(@RequestBody Group group, @PathVariable long groupid)
    {
        Group newGroup = new Group();
        //Group newGroup = groupService.findById(groupid);

        newGroup.setGroupid(groupid);
        newGroup.setGroup_name(group.getGroup_name());
        newGroup.setAdminid(group.getAdminid());
        newGroup.setInvite_code(group.getInvite_code());
        groupRepo.save(newGroup);

        return new ResponseEntity<>(newGroup,HttpStatus.OK);


    }

}








