package com.example.musimatch.models;

import android.os.Build;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.example.musimatch.models.enums.PostType;
import com.example.musimatch.services.LoginService;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PostModel {
    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<Pair<Post, Post>> linkedPostsList = new ArrayList<>();
    ArrayList<Pair<Post, Tag>> tagsOfPosts = new ArrayList<>();

    public final static PostModel instance = new PostModel();

    @RequiresApi(api = Build.VERSION_CODES.O)
    private PostModel() {
        initializePostsList();
        initializeLinkedPostsList();
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

    public void linkPosts(Post post1, Post post2)
    {
        if(!doesLinkAlreadyExist(post1, post2))
        {
            linkedPostsList.add(new Pair<Post, Post>(post1, post2));
            post1.addLinkedPost(post2);
            post2.addLinkedPost(post1);
        }
    }

    private boolean doesLinkAlreadyExist(Post post1, Post post2)
    {
        for(Pair<Post, Post> pair: linkedPostsList)
        {
          if((pair.first.equals(post1) && pair.second.equals(post2)) ||
                  (pair.first.equals(post2) && pair.second.equals(post1)))
            {
                return true;
            }
        }
        return false;
    }

    public void addTagToPost(Post post, Tag tag)
    {
        if(!doesPostAlreadyHaveThisTag(post, tag))
        {
            tagsOfPosts.add(new Pair<Post, Tag>(post, tag));
            post.addTag(tag);
        }
    }

    private boolean doesPostAlreadyHaveThisTag(Post post, Tag tag)
    {
        for(Pair<Post, Tag> pair: tagsOfPosts)
        {
            if(pair.first.equals(post) && pair.second.equals(tag))
            {
                return true;
            }
        }
        return false;
    }

    public void addOrUpdateSerialRater(Post post, SerialRater serialRater)
    {
        if(!post.getCreator().equals(LoginService.getUser()))
        {
            for(int i = 0; i < post.getSerialRaters().size(); i++)
            {
                if(post.getSerialRaters().get(i).getUserWhoRated().equals(serialRater.getUserWhoRated()))
                {
                    removeOldSerialRater(post, i);
                }
            }
            post.addSerialRater(serialRater);
        }
    }

    private void removeOldSerialRater(Post post, int i)
    {
        ArrayList<SerialRater> newSerialRaters = post.getSerialRaters();
        newSerialRaters.remove(newSerialRaters.get(i));
        post.setSerialRaters(newSerialRaters);
    }

    public ArrayList<Post> findPostsByUserAndPostType(User user, PostType postType) {
        ArrayList<Post> relevantPosts = new ArrayList<>();
        for (Post post : posts)
        {
            if(post.getCreator().equals(user) && post.getPostType().equals(postType))
            {
                relevantPosts.add(post);
            }
        }
        return relevantPosts;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializePostsList()
    {
        for(int i = 0; i < 10; i++)
        {
            posts.add(new Post((long)i, "Post" + i, "I love cats\nThey are so cute\nI want to have one",
                    "", UserModel.instance.findUserById((long)i),
                    PostType.POEM));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeLinkedPostsList()
    {
        linkPosts(posts.get(0),posts.get(1));
        linkPosts(posts.get(3),posts.get(7));
        linkPosts(posts.get(3),posts.get(6));
        linkPosts(posts.get(3),posts.get(1));
    }
}
