package com.lambdaschool.wellness.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.repository.GroupRepository;
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
import java.util.Random;


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

    @GetMapping("/all/admin/id/{adminId}")
    public Group getGroupsByAdminId(@PathVariable String adminId) throws Exception {
        DecodedJWT decodedJWT = JWTHelper.decodeJWTWithVerify(request);
        if(decodedJWT.getSubject() == adminId) {
            return groupRepo.findAllByAdminId(adminId);
        } else {
            return null;
        }
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
        //Save the new group into the database
//        newGroup.getUserIds().add(decodedJWT.getSubject());
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
//        group.getUserIds().add(decodedJWT.getSubject());
        return group;
    }


    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long groupId)
    {
        Group group = groupRepo.findByGroupId(groupId);
        groupRepo.delete(group);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

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








