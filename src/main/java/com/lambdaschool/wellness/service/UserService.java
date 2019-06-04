package com.lambdaschool.wellness.service;

import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class UserService
{

    @Autowired
    private UserRepository userRepository;


    public Iterable<User>findAll(){

        return userRepository.findAll();
    }

    public User findById(Long id){

        return userRepository.getById(id);
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public void delete(Long id)
    {
        User user = findById(id);
        userRepository.delete(user);
    }

    public void updateUser(long id, User user)
    {
        userRepository.save(user);

    }







}
