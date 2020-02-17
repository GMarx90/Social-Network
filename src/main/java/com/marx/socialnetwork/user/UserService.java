package com.marx.socialnetwork.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user")
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

@PostMapping("/user")
    public HttpStatus addUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getName());  //nie widzi że jest @Data (czyli między innymi @Getter)
        if (existingUser.isPresent()) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }


    @DeleteMapping("/delete")
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