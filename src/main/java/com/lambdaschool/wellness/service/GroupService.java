package com.lambdaschool.wellness.service;

import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService
{
    @Autowired
    private GroupRepository groupRepository;


    public Iterable<Group>findAll(){

        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findByGroupid(id);
    }

    public Group save(Group newGroup)
    {
        return groupRepository.save(newGroup);
    }

    public void delete(Long groupid)
    {
        Group group = findById(groupid);
        groupRepository.delete(group);
    }

    public void updateGroup(Long groupid, Group group)
    {
        groupRepository.save(group);

    }

}


