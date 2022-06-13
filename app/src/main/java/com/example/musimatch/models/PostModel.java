package com.example.musimatch.models;

import android.os.Build;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.example.musimatch.models.enums.PostType;
import com.example.musimatch.services.LoginService;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PostModel {
    Long sequence = 400L;
    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<Pair<Post, Post>> linkedPostsList = new ArrayList<>();
    ArrayList<Pair<Post, Tag>> tagsOfPosts = new ArrayList<>();

    public final static PostModel instance = new PostModel();

    public Long getSequence() {
        return (sequence++);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private PostModel() {
        initializePostsList();
        initializeLinkedPostsList();
    }

    public ArrayList<Post> getAllPosts()
    {
        return posts;
    }

    public void removePost(Post post)
    {
        if(posts.contains(post))
        {
            posts.remove(post);
        }
    }

    public void updatePost(Post post)
    {
        for(Post currentPost: posts)
        {
            if(currentPost.getId().equals(post.getId()))
            {
                removePost(currentPost);
                posts.add(post);
                return;
            }
        }
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

    public ArrayList<Post> findPostsByUser(User user) {
        ArrayList<Post> relevantPosts = new ArrayList<>();
        for (Post post : posts)
        {
            if(post.getCreator().equals(user))
            {
                relevantPosts.add(post);
            }
        }
        return relevantPosts;
    }

    public ArrayList<Post> findLinkedPosts(Post destinationPost) {
        ArrayList<Post> relevantPosts = new ArrayList<>();
        for (Pair<Post, Post> pair : linkedPostsList)
        {
            if(pair.first.equals(destinationPost))
            {
                relevantPosts.add(pair.second);
            }
            else if (pair.second.equals(destinationPost)) {
                relevantPosts.add(pair.first);
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
        posts.add(new Post(0L, "Girls Like Her", "I wish there were\nmore girls like her\nBecause maybe then" +
                "\nI'd feel better\nShe doesn't love me\nand that's so sad\nbut nevermind",
                null, UserModel.instance.findUserById(0L),
                PostType.POEM));
        posts.get(0).addTag(TagModel.instance.findTagById(0L));
        posts.get(0).addTag(TagModel.instance.findTagById(1L));

        posts.add(new Post(1L, "Angels Are Real", "He's an angel\nThis I know\nWho looks like a devil" +
                "\nBut I love him so!",
                null, UserModel.instance.findUserById(1L),
                PostType.POEM));
        posts.get(1).addTag(TagModel.instance.findTagById(3L));
        posts.get(1).addTag(TagModel.instance.findTagById(5L));


        posts.add(new Post(2L, "Pain", "It rhymes with rain\nNot hard to gain\nof course I'm speaking of" +
                "\nmy internal pain",
                null, UserModel.instance.findUserById(1L),
                PostType.POEM));
        posts.get(2).addTag(TagModel.instance.findTagById(4L));
        posts.get(2).addTag(TagModel.instance.findTagById(11L));
        posts.get(2).addTag(TagModel.instance.findTagById(9L));


        posts.add(new Post(3L, "Love Can Heal", "Love can heal\nBodies and souls\nbut love can kill" +
                "\nAnd change all your goals\nI want to stay single\nAnd ready to mingle",
                null, UserModel.instance.findUserById(2L),
                PostType.POEM));
        posts.get(3).addTag(TagModel.instance.findTagById(7L));
        posts.get(3).addTag(TagModel.instance.findTagById(8L));


        posts.add(new Post(4L, "La La La", "I'm covering my ears\nlike a kid\nWhen your words mean nothing" +
                "\nI go la la la\nturning up the volume\nwhen you speak\ncause if my heart\ncant's stop it\nI'll find a way\nto block it",
                null, UserModel.instance.findUserById(2L),
                PostType.POEM));
        posts.get(4).addTag(TagModel.instance.findTagById(10L));
        posts.get(4).addTag(TagModel.instance.findTagById(11L));


        posts.add(new Post(5L, "Instagram Guy", "Uses filters\nLike a drama queen\nLooks like the faith" +
                "\nBut feels like a sin",
                null, UserModel.instance.findUserById(2L),
                PostType.POEM));
        posts.get(5).addTag(TagModel.instance.findTagById(12L));
        posts.get(5).addTag(TagModel.instance.findTagById(1L));


        posts.add(new Post(6L, "Over Now", "It's Over Now\nOver Now\nNo body cares" +
                "\n\nIt's Over Now\nOver Now\nNo body cares\n\nIt's Over Now\nOver Now\nNo body cares",
                null, UserModel.instance.findUserById(3L),
                PostType.POEM));
        posts.get(6).addTag(TagModel.instance.findTagById(9L));


        posts.add(new Post(7L, "Trupi Rupi", "I love saying nothing\nI love food too\nTrupi Rupi" +
                "\nJust say I do",
                null, UserModel.instance.findUserById(4L),
                PostType.POEM));
        posts.get(7).addTag(TagModel.instance.findTagById(8L));
        posts.get(7).addTag(TagModel.instance.findTagById(1L));
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
