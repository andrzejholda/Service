package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.Repository.PostsRepository;
import com.example.demo.model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class PostService {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestHeader("username")String username,@RequestBody String postBody){
        Optional<User> userFromDB = userRepository.findByUsername(username);
if(!userFromDB.isEmpty()){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
        Post post = new Post(userFromDB.get(), postBody);
        Post savedPost = postsRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }
}
