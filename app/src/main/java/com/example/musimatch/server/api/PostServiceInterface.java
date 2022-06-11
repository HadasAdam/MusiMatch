package com.example.musimatch.server.api;

import com.example.musimatch.models.Comment;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.SerialRater;
import com.example.musimatch.models.User;

import java.util.List;

public interface PostServiceInterface {
    Post getPostById(Long id);
    List<Post> getPostsByUser(User user);
    void addSerialRaterToPost(Post post, SerialRater serialRater);
    boolean hasUserASerialRaterInPost(Post post, User user);
    void addNewPost(Post post);
    void updatePost(Post post);
    void linkPostToPost(Post sourcePost, Post linkPost);
    void addCommentToPost(Post post, Comment comment);
}
