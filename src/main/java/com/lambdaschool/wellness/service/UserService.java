package com.lambdaschool.wellness.service;
import com.lambdaschool.wellness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lambdaschool.wellness.model.User;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public Iterable<User>findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){

        return userRepository.getById(id);
    }

    public User saveOrUpdateUser(User user)
    {
        return userRepository.save(user);
    }
}
