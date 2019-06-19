package com.lambdaschool.wellness.service;

import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    public User findById(Long userid) {
        return userRepository.findByUserid(userid);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long userid) {
        User user = findById(userid);
        userRepository.delete(user);
    }

    public User findByLname(String lname) {
        return userRepository.findByLname(lname);
    }

    public User saveUser(User updateduser) {
        return userRepository.save(updateduser);
    }

    public User findByUserid(Long userid) {
        return userRepository.findByUserid(userid);
    }

}
