package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{
    User findByUserid(long userid);
    User findByLname(String lname);
    User findById(long id);



}
