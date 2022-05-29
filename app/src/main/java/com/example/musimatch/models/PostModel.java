package com.example.musimatch.models;

import com.example.musimatch.models.enums.PostType;

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

    public Post findPostById(Long id) {
        for (Post post : posts)
        {
            if(post.getId() == (long)id)
            {
                return post;
            }
        }
        return null;
    }

    public ArrayList<Post> findPostsByUserId(String userId) {
        ArrayList<Post> relevantPosts = new ArrayList<>();
        for (Post post : posts)
        {
            if(post.getCreator().getId().equals(userId))
            {
                relevantPosts.add(post);
            }
        }
        return relevantPosts;
    }

    public ArrayList<Post> findPostsByUserIdAndPostType(String userId, PostType postType) {
        ArrayList<Post> relevantPosts = new ArrayList<>();
        for (Post post : posts)
        {
            if(post.getCreator().getId().equals(userId) && post.getPostType() == postType)
            {
                relevantPosts.add(post);
            }
        }
        return relevantPosts;
    }

    private void initializePostsList()
    {
        for(int i = 0; i < 10; i++)
        {
            posts.add(new Post((long)i, "Post" + i, "I love cats\nThey are so cute\nI want to have one",
                    "", UserModel.instance.findUserById(String.valueOf(i)),
                    PostType.POEM,null, null, null,null));
        }
    }
}
