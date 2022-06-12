package com.example.musimatch.server.ejb;

import com.example.musimatch.models.Comment;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.SerialRater;
import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.server.api.PostServiceInterface;
import com.example.musimatch.server.api.PostServiceLocal;

import java.util.List;

public class PostServiceEJB implements PostServiceInterface, PostServiceLocal {
    public static final PostServiceEJB instance = new PostServiceEJB();
    private static final GeneralMusimatchDaoLocal daoService = GeneralMusimatchDaoEJB.instance;

    @Override
    public Post getPostById(Long id) {
        //TODO: return daoService.findById(Post.getTableName(), id);
        return null;
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return null;
    }

    @Override
    public void addSerialRaterToPost(Post post, SerialRater serialRater) {

    }

    @Override
    public boolean hasUserASerialRaterInPost(Post post, User user) {
        return false;
    }

    @Override
    public void addNewPost(Post post) {

    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void linkPostToPost(Post sourcePost, Post linkPost) {

    }

    @Override
    public void addCommentToPost(Post post, Comment comment) {

    }
}
