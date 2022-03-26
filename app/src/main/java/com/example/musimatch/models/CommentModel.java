package com.example.musimatch.models;

import java.util.ArrayList;
import java.util.Date;

public class CommentModel {
    ArrayList<Comment> comments = new ArrayList<>();

    public final static CommentModel instance = new CommentModel();

    private CommentModel() {
        initializeCommentsList();
    }

    public ArrayList<Comment> getAllComments()
    {
        return comments;
    }

    public void createComment(Comment comment) {
        comments.add(comment);
    }

    public Comment findCommentById(String id) {
        for (Comment comment : comments)
        {
            if(comment.getId().equals(id))
            {
                return comment;
            }
        }
        return null;
    }

    private ArrayList<Comment> getCommentsByPostId(String postId) {
        ArrayList<Comment> commentsOfPost = new ArrayList<>();
        for(Comment comment : comments)
        {
            if(comment.getPostId().endsWith(postId))
            {
                commentsOfPost.add(comment);
            }
        }
        return commentsOfPost;
    }

    private void initializeCommentsList()
    {
        for(int i = 0; i < 10; i++)
        {
            comments.add(new Comment(String.valueOf(i), String.valueOf(i%2),String.valueOf(i),"Love it! " + i , new Date()));
        }
    }
}
