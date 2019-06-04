package com.lambdaschool.wellness.controllers;


import com.lambdaschool.wellness.model.User;
import com.lambdaschool.wellness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
//@CrossOrigin("http://localhost:3000")
public class UserController
{
    @Autowired
    private UserService userService;




    @GetMapping("/all")
    public Iterable<User>getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> addUsers(@Valid @RequestBody User user, BindingResult result)
    {
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                //expound from postman
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap,HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }





}
