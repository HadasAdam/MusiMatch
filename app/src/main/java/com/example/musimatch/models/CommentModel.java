package com.example.musimatch.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommentModel {
    ArrayList<Comment> comments = new ArrayList<>();

    public final static CommentModel instance = new CommentModel();

    @RequiresApi(api = Build.VERSION_CODES.O)
    private CommentModel() {
        initializeCommentsList();
    }

    public ArrayList<Comment> getAllComments()
    {
        return comments;
    }

    public Comment findCommentById(Long id) {
        for (Comment comment : comments)
        {
            if(comment.getId() == (long)id)
            {
                return comment;
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeCommentsList()
    {
        ArrayList<Post> posts = PostModel.instance.getAllPosts();
        for(int i = 0; i < posts.size(); i++)
        {
            for(int j = 0; j < 4; j++)
            {
                Comment comment = new Comment((long)(i*10+j), UserModel.instance.findUserById((long)(i%2)), posts.get(i),"Comment "+(i*10+j), new Date());
                comments.add(comment);
                posts.get(i).addComment(comment);
            }
        }
    }
}
