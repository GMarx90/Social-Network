package com.marx.socialnetwork.post;

import com.marx.socialnetwork.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NonNull
    private User user;
    @NonNull
    private String body;
//
//    public Post(User user, String body) {
//        this.user=user;
//        this.body=body;
//    }
}