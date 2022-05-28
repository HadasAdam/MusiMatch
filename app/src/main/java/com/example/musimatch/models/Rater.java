package com.example.musimatch.models;

import androidx.room.Entity;

import com.example.musimatch.models.enums.PostType;

@Entity
public class Rater {
    Long id;
    PostType postType;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
