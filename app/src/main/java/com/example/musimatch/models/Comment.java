package com.example.musimatch.models;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class Comment {
    private Long id;
    private User user;
    private Post post;
    private String content;
    private Date commentUploadTime;

    public Comment(){}

    public Comment(Long id, User user, Post post, String content, Date commentUploadTime) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.content = content;
        this.commentUploadTime = commentUploadTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentUploadTime() {
        return commentUploadTime;
    }

    public void setCommentUploadTime(Date commentUploadTime) {
        this.commentUploadTime = commentUploadTime;
    }
}
