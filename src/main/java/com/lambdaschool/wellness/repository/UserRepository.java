package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.Group;
import com.lambdaschool.wellness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByAuth0id(String auth0id);
}