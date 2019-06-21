package com.lambdaschool.wellness.controllers;

import com.auth0.jwk.Jwk;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.repository.UserRepository;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    private Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
    @GetMapping("/auth0id")
    private User getUserByAuth0Id() throws Exception {
        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);

        User member = userRepo.findByAuth0id(decodedJWT.getSubject());
        return member;
    }
    @PostMapping("")
    User createUser() throws Exception {
        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);

        if(userRepo.existsByAuth0id(decodedJWT.getSubject())) {
            return null;
        }
        User newUser = new User(decodedJWT.getSubject());
        userRepo.save(newUser);
        return newUser;
    }

//    @GetMapping("/")
//    public Iterable<Group> getGroupsByAuth0Id() throws Exception {
//        String authHeader = request.getHeader("Authorization").split(" ")[1];
//        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
//        Jwk jwk = JWTHelper.getJwk(decodedJWT);
//        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);
//        return
//    }

}
