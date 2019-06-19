package com.lambdaschool.wellness.controllers;
//https:////wellness-bet-backend.herokuapp.com/api/user/2

import com.auth0.jwk.Jwk;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.service.Auth0.JWTHelper;
import com.lambdaschool.wellness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/all")
    public Iterable<User> getAllUsers() throws Exception {

        String authHeader = request.getHeader("Authorization").split(" ")[1];
        DecodedJWT decodedJWT = JWTHelper.getDecodedJWT(authHeader);
        Jwk jwk = JWTHelper.getJwk(decodedJWT);
        JWTHelper.verifyDecodedJWT(jwk, decodedJWT);

        return userService.findAll();

    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getByUserid(@PathVariable Long userid) {
        User user = userService.findByUserid(userid);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // @GetMapping(value ="/{lname}")
    // public ResponseEntity<?> getUsersByLname(@PathVariable String lname)
    // {
    // userService.findByLname(lname);
    // return new ResponseEntity<>(lname,HttpStatus.OK);
    // }

    @PostMapping("")
    public ResponseEntity<?> addNewUsers(@Valid @RequestBody User newUser) throws URISyntaxException {
        newUser = userService.save(newUser);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/userid")
                .buildAndExpand(newUser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> findById(@PathVariable Long userid) {
        userService.delete(userid);
        return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);

    }

    @PutMapping("/{userid}")
    public ResponseEntity<?> saveUser(@RequestBody User user, @PathVariable long userid) {
        User newUser = new User();

        newUser.setUserid(userid);
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setEmail(user.getEmail());
        userService.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }

}
