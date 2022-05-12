package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserService {

     @Autowired
     UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;  //return information in JSON
    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/users")                           //showing users
    public ResponseEntity getUsers() throws JsonProcessingException {

       List<User> users=userRepository.findAll();
       return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }
    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){      //add users
  //  List<User> userFromDB=userRepository.findByUsername(user.getUsername()); / giving back full list but no need
        Optional <User>userFromDB=userRepository.findByUsername(user.getUsername());
  //  if (!userFromDB.isEmpty()){
        if(userFromDB.isPresent()){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
    User savedUser=userRepository.save(user);
    return ResponseEntity.ok(savedUser);
    }
    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/login")  //login
    public ResponseEntity login(@RequestBody User user){
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB.isEmpty()||wrongPassword(userFromDB,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            
        }
        return ResponseEntity.ok().build();
    }

    private boolean wrongPassword(Optional<User> userFromDB, User user) {
        return !userFromDB.get().getPassword().equals(user.getPassword());
    }
}
