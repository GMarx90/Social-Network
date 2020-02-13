package com.marx.socialweb.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {

    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

/*
public ResponseEntity getAllUsers() throws JsonProcessingException {
        List<User> users =userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
}*/

    public HttpStatus addUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getName());
        if (existingUser.isPresent()) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }

    /*
    public HttpStatus deleteUser(@PathVariable ("name") String name){
    */


    @DeleteMapping("/")
    public HttpStatus deleteUser(@RequestBody User user) {
        Optional<User> existingUserForDelete = userRepository.findByUserName(user.getName());
        if (existingUserForDelete.isPresent()) {
            userRepository.delete(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }


}