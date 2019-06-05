package com.lambdaschool.wellness.repository;

import com.lambdaschool.wellness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);
}
