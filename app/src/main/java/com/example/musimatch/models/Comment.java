package com.example.musimatch.models;

import java.util.Date;

public class Comment {
    private String id;
    private String userId;
    private String postId;
    private String content;
    private Date commentUploadTime;

    public Comment(){}

    public Comment(String id, String userId, String postId, String content, Date commentUploadTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.commentUploadTime = commentUploadTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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
