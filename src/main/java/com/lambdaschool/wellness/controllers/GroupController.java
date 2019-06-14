package com.lambdaschool.wellness.controllers;

import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.service.GroupService;
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
@RequestMapping(value = "/api/group")
@CrossOrigin("http:localhost:3000")

public class GroupController
{
    @Autowired
    private GroupService groupService;

    @GetMapping("/all")
    public Iterable<Group> getAllGroups()
    {
        return groupService.findAll();
    }

    @GetMapping("/{groupid}")
    public ResponseEntity<?> getGroupById(@PathVariable Long groupid)
    {
        Group group = groupService.findById(groupid);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addNewGroup(@Valid @RequestBody Group newGroup) throws URISyntaxException
    {
        newGroup = groupService.save(newGroup);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/groupid").buildAndExpand(newGroup.getGroupid()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newGroup,HttpStatus.CREATED);
    }



    @DeleteMapping("/{groupid}")
    public ResponseEntity<?> deleteGroupById(@PathVariable Long groupid)
    {
        groupService.delete(groupid);
        return new ResponseEntity<String>("Group deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{groupid}")
    public ResponseEntity<?> saveGroup(@RequestBody Group group, @PathVariable long groupid)
    {
        Group newGroup = new Group();

        newGroup.setGroupid(groupid);
        newGroup.setGroup_name(group.getGroup_name());
        newGroup.setGoal(group.getGoal());
        newGroup.setAdmin(group.getAdmin());
        newGroup.setInvite_code(group.getInvite_code());
        groupService.save(newGroup);

        return new ResponseEntity<>(newGroup,HttpStatus.OK);


    }

}








