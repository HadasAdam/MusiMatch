package com.example.musimatch.models;

import android.os.Build;
import android.util.Pair;
import androidx.annotation.RequiresApi;

import com.example.musimatch.models.enums.PoemRateSections;
import com.example.musimatch.models.enums.PostType;
import com.example.musimatch.services.LoginService;
import java.util.ArrayList;
import java.util.Date;

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
    private void initializePostsList() {
        posts.add(new Post(0L, "Girls Like Her", "I wish there were\nmore girls like her\nBecause maybe then" +
                "\nI'd feel better\nShe doesn't love me\nand that's so sad\nbut nevermind",
                null, UserModel.instance.findUserById(0L),
                PostType.POEM));
        posts.get(0).addTag(TagModel.instance.findTagById(0L));
        posts.get(0).addTag(TagModel.instance.findTagById(1L));
        posts.get(0).addComment(new Comment(1L, UserModel.instance.findUserById(6L), posts.get(0), "there are a lot of fishes in the ocean...", new Date()));
        posts.get(0).addComment(new Comment(2L, UserModel.instance.findUserById(1L), posts.get(0), "you will find the one, i'm sure", new Date()));
        posts.get(0).addComment(new Comment(3L, UserModel.instance.findUserById(3L), posts.get(0), "you can find better", new Date()));
        posts.get(0).addComment(new Comment(4L, UserModel.instance.findUserById(2L), posts.get(0), "that is sad, so why nevemind? lol", new Date()));

        posts.add(new Post(1L, "Angels Are Real", "He's an angel\nThis I know\nWho looks like a devil" +
                "\nBut I love him so!",
                null, UserModel.instance.findUserById(1L),
                PostType.POEM));
        posts.get(1).addTag(TagModel.instance.findTagById(3L));
        posts.get(1).addTag(TagModel.instance.findTagById(5L));
        posts.get(1).addComment(new Comment(1L, UserModel.instance.findUserById(1L), posts.get(1), "nice one", new Date()));
        posts.get(1).addComment(new Comment(2L, UserModel.instance.findUserById(3L), posts.get(1), "are you sure? angels are not real! lol", new Date()));


        posts.add(new Post(2L, "Pain", "It rhymes with rain\nNot hard to gain\nof course I'm speaking of" +
                "\nmy internal pain",
                null, UserModel.instance.findUserById(5L),
                PostType.POEM));
        posts.get(2).addTag(TagModel.instance.findTagById(4L));
        posts.get(2).addTag(TagModel.instance.findTagById(11L));
        posts.get(2).addTag(TagModel.instance.findTagById(9L));
        posts.get(2).addComment(new Comment(1L, UserModel.instance.findUserById(1L), posts.get(2), "wow lovely!!!", new Date()));
        posts.get(2).addComment(new Comment(2L, UserModel.instance.findUserById(3L), posts.get(2), "great song, gonna be a hit", new Date()));


        posts.add(new Post(3L, "Love Can Heal", "Love can heal\nBodies and souls\nbut love can kill" +
                "\nAnd change all your goals\nI want to stay single\nAnd ready to mingle",
                null, UserModel.instance.findUserById(2L),
                PostType.POEM));
        posts.get(3).addTag(TagModel.instance.findTagById(7L));
        posts.get(3).addTag(TagModel.instance.findTagById(8L));
        posts.get(3).addComment(new Comment(1L, UserModel.instance.findUserById(9L), posts.get(3), "well, love can kill. it's true", new Date()));
        posts.get(3).addComment(new Comment(2L, UserModel.instance.findUserById(1L), posts.get(3), "I want to stay single as well", new Date()));

        posts.add(new Post(4L, "La La La", "I'm covering my ears\nlike a kid\nWhen your words mean nothing" +
                "\nI go la la la\nturning up the volume\nwhen you speak\ncause if my heart\ncant's stop it\nI'll find a way\nto block it",
                null, UserModel.instance.findUserById(9L),
                PostType.POEM));
        posts.get(4).addTag(TagModel.instance.findTagById(10L));
        posts.get(4).addTag(TagModel.instance.findTagById(11L));
        posts.get(4).addComment(new Comment(1L, UserModel.instance.findUserById(6L), posts.get(4), "i just l-o-v-e that song!!", new Date()));


        posts.add(new Post(5L, "Instagram Guy", "Uses filters\nLike a drama queen\nLooks like the faith" +
                "\nBut feels like a sin",
                null, UserModel.instance.findUserById(8L),
                PostType.POEM));
        posts.get(5).addTag(TagModel.instance.findTagById(12L));
        posts.get(5).addTag(TagModel.instance.findTagById(1L));
        posts.get(5).addComment(new Comment(1L, UserModel.instance.findUserById(8L), posts.get(5), "what are you thinking guys? would love to here your thoughts", new Date()));
        posts.get(5).addComment(new Comment(2L, UserModel.instance.findUserById(4L), posts.get(5), "not bad...", new Date()));
        posts.get(5).addComment(new Comment(3L, UserModel.instance.findUserById(9L), posts.get(5), "love it", new Date()));
        posts.get(5).addComment(new Comment(4L, UserModel.instance.findUserById(1L), posts.get(5), "i dont use instagram but it's looks good", new Date()));


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


        posts.add(new Post(8L, "You Fit Into Me", "you fit into me\n like a hook into an eye\n a fish hook\n an open eye",
                null, UserModel.instance.findUserById(5L), PostType.POEM));
        posts.get(8).addTag(TagModel.instance.findTagById(8L));
        posts.get(8).addTag(TagModel.instance.findTagById(1L));
        posts.get(8).addComment(new Comment(1L, UserModel.instance.findUserById(9L), posts.get(8), "like a hook into my heart, great song, i will try to make a melody to this one!", new Date()));
        posts.get(8).addComment(new Comment(2L, UserModel.instance.findUserById(7L), posts.get(8), "great poem", new Date()));


        posts.add(new Post(9L, "Risk", "And then the day came,\n when the risk\n" +
                "to remain tight\n" +
                "in a bud\n" +
                "was more painful\n" +
                "than the risk\n" +
                "it took\n" +
                "to blossom.",
                null, UserModel.instance.findUserById(6L), PostType.POEM));
        posts.get(9).addTag(TagModel.instance.findTagById(2L));
        posts.get(9).addTag(TagModel.instance.findTagById(3L));
        posts.get(9).addComment(new Comment(1L, UserModel.instance.findUserById(2L), posts.get(9), "hard..", new Date()));
        posts.get(9).addComment(new Comment(2L, UserModel.instance.findUserById(1L), posts.get(9), "powerful", new Date()));


        posts.add(new Post(10L, "Invisible Fish", "Invisible fish swim this ghost ocean now described by waves of sand, by water-worn rock.\n Soon the fish will learn to walk. Then humans will come ashore and paint dreams on the dying stone." +
                "\n Then later, much later, the ocean floor will be punctuated by Chevy trucks, carrying the dreamers’ decendants, who are going to the store.",
                null, UserModel.instance.findUserById(7L), PostType.POEM));
        posts.get(10).addTag(TagModel.instance.findTagById(13L));
        posts.get(10).addTag(TagModel.instance.findTagById(7L));
        posts.get(10).addComment(new Comment(1L, UserModel.instance.findUserById(9L), posts.get(10), "hey kiki, good poem", new Date()));
        posts.get(10).addComment(new Comment(2L, UserModel.instance.findUserById(5L), posts.get(10), "amazing", new Date()));
        posts.get(10).addComment(new Comment(3L, UserModel.instance.findUserById(6L), posts.get(10), "insightful..!", new Date()));


        posts.add(new Post(11L, "So Tired Blues", "With the sun in my hand\n" +
                "Gonna throw the sun\n" +
                "Way across the land\n" +
                "Cause I’m tired,\n" +
                "Tired as I can be",
                null, UserModel.instance.findUserById(1L), PostType.POEM));
        posts.get(11).addTag(TagModel.instance.findTagById(7L));
        posts.get(11).addTag(TagModel.instance.findTagById(9L));
        posts.get(11).addComment(new Comment(1L, UserModel.instance.findUserById(5L), posts.get(11), "just let me sleep !!!", new Date()));
//        posts.get(11).addSerialRater(new SerialRater(1L, PoemRateSections.DEPT ,null, 4, UserModel.instance.findUserById(3L), posts.get(11)));
//        posts.get(11).addSerialRater(new SerialRater(2L, PoemRateSections.LANGUAGE ,null, 5, UserModel.instance.findUserById(5L), posts.get(11)));
//        posts.get(11).addSerialRater(new SerialRater(3L, PoemRateSections.RHYMES ,null, 4, UserModel.instance.findUserById(5L), posts.get(11)));


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeLinkedPostsList()
    {
        linkPosts(posts.get(0),posts.get(1));
        linkPosts(posts.get(3),posts.get(7));
        linkPosts(posts.get(3),posts.get(6));
        linkPosts(posts.get(3),posts.get(1));

        linkPosts(posts.get(10),posts.get(7));
        linkPosts(posts.get(11),posts.get(10));
        linkPosts(posts.get(8),posts.get(2));
        linkPosts(posts.get(9),posts.get(4));
        linkPosts(posts.get(6),posts.get(5));
    }
}
