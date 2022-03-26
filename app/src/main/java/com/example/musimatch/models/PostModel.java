package com.example.musimatch.models;

import java.util.ArrayList;

public class PostModel {
    ArrayList<Post> posts = new ArrayList<>();

    public final static PostModel instance = new PostModel();

    private PostModel() {
        initializePostsList();
    }

    public ArrayList<Post> getAllPosts()
    {
        return posts;
    }

    public void createPost(Post post) {
        posts.add(post);
    }

    public Post findPostById(String id) {
        for (Post post : posts)
        {
            if(post.getId().equals(id))
            {
                return post;
            }
        }
        return null;
    }

    private void initializePostsList()
    {
        for(int i = 0; i < 10; i++)
        {
            posts.add(new Post(String.valueOf(i), "Post" + 1, "I love cats\nThey are so cute\nI want to have one",
                    "", String.valueOf(i), PostType.POEM, 0d, null, null, null));
        }
    }
}
