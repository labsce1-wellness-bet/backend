package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long>
{
    Group findByGroupid(long groupid);
    Group  findById(long id);

}
