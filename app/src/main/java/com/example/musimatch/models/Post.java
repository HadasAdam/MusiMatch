package com.example.musimatch.models;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Post implements Serializable {
    private Long id;
    private String title;
    private String poemLyrics;
    private String melodyFilePath;
    private User creator;
    private PostType postType;
    private Double averagePostRate;

    private ArrayList<Post> linkedPosts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Tag> tags = new ArrayList<>();
    private ArrayList<SerialRater> serialRaters = new ArrayList<>();

    public Post() { }

    public Post(Long id, String title, String poemLyrics, String melodyFilePath, User creator,
                PostType postType, Double averagePostRate, ArrayList<Post> linkedPosts, ArrayList<Comment> comments,
                ArrayList<Tag> tags, ArrayList<SerialRater> serialRaters) {
        this.id = id;
        this.title = title;
        this.poemLyrics = poemLyrics;
        this.melodyFilePath = melodyFilePath;
        this.creator = creator;
        this.postType = postType;
        this.averagePostRate = averagePostRate;
        this.linkedPosts = linkedPosts;
        this.comments = comments;
        this.tags = tags;
        this.serialRaters = serialRaters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoemLyrics() {
        return poemLyrics;
    }

    public void setPoemLyrics(String poemLyrics) {
        this.poemLyrics = poemLyrics;
    }

    public String getMelodyFilePath() {
        return melodyFilePath;
    }

    public void setMelodyFilePath(String melodyFilePath) {
        this.melodyFilePath = melodyFilePath;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Double getAveragePostRate() {
        return averagePostRate;
    }

    public void setAveragePostRate(Double averagePostRate) {
        this.averagePostRate = averagePostRate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Post> getLinkedPosts() {
        return linkedPosts;
    }

    public void setLinkedPosts(ArrayList<Post> linkedPosts) {
        this.linkedPosts = linkedPosts;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<SerialRater> getSerialRaters() {
        return serialRaters;
    }

    public void setSerialRaters(ArrayList<SerialRater> serialRaters) {
        this.serialRaters = serialRaters;
    }

    public void addComment(Comment comment)
    {
        this.comments.add(comment);
    }

    public void addTag(Tag tag)
    {
        this.tags.add(tag);
    }

    public void addLinkedPost(Post postToLink)
    {
        this.linkedPosts.add(postToLink);
    }

    public void addSerialRater(SerialRater serialRater)
    {
        this.serialRaters.add(serialRater);
    }
}
