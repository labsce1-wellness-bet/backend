package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long>
{
    Group findByGroupId(long groupId);
    Group findByAdminId(String adminId);
    Group findAllByAdminId(String adminId);
    Group findBySecretCode(String secretCode);
}
