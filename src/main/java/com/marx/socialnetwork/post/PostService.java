package com.marx.socialnetwork.post;


import com.marx.socialnetwork.user.User;
import com.marx.socialnetwork.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostService {

    //    @Autowired
    PostRepository postRepository;
    //    @Autowired
    UserRepository userRepository;

    @PostMapping("/newpost")
    public HttpStatus addPost(@RequestHeader("username") String name, @RequestBody String postBody) {
        Optional<User> exsitingUser = userRepository.findByUserName(name);
        if (exsitingUser.isEmpty()) {
            return HttpStatus.UNAUTHORIZED;
        } else {
            Post post = new Post(exsitingUser.get(), postBody);
            Post savedPost = postRepository.save(post);
            return HttpStatus.ACCEPTED;
        }
    }
}
