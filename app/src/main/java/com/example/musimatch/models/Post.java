package com.example.musimatch.models;

import android.util.Log;

import androidx.room.Entity;

import com.example.musimatch.models.enums.MelodyRateSections;
import com.example.musimatch.models.enums.PoemRateSections;
import com.example.musimatch.models.enums.PostType;

import org.intellij.lang.annotations.Identifier;

import java.io.Serializable;
import java.util.ArrayList;

//@Entity //this is not the right annotation - use javax.persistence
//@Table(name = "POSTS")
public class Post implements Serializable {
    private Long id;
    private String title;
    private String poemLyrics;
    private String melodyFilePath;
    private User creator;
    private PostType postType;
    private AverageRater averageRater;
    private Double averagePostRate;

    private ArrayList<Post> linkedPosts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Tag> tags = new ArrayList<>();
    private ArrayList<SerialRater> serialRaters = new ArrayList<>();

    public Post() {
        this.postType = PostType.POEM;
        this.averageRater = new AverageRater();
        this.averagePostRate = 0D;
    }

    public Post(Long id, String title, String poemLyrics, String melodyFilePath, User creator,
                PostType postType, ArrayList<Post> linkedPosts, ArrayList<Comment> comments,
                ArrayList<Tag> tags, ArrayList<SerialRater> serialRaters) {
        this.id = id;
        this.title = title;
        this.poemLyrics = poemLyrics;
        this.melodyFilePath = melodyFilePath;
        this.creator = creator;
        this.postType = postType;
        this.averageRater = new AverageRater();
        this.averagePostRate = 0D;
        this.linkedPosts = linkedPosts;
        this.comments = comments;
        this.tags = tags;
        this.serialRaters = serialRaters;

        if(this.postType == null)
        {
            this.postType = PostType.POEM;
        }
    }

    public Post(Long id, String title, String poemLyrics, String melodyFilePath,
                User creator, PostType postType) {
        this.id = id;
        this.title = title;
        this.poemLyrics = poemLyrics;
        this.melodyFilePath = melodyFilePath;
        this.creator = creator;
        this.postType = postType;
        this.averageRater = new AverageRater();
        this.averagePostRate = 0D;

        if(this.postType == null)
        {
            this.postType = PostType.POEM;
        }
    }

//    @Id
//    @GeneratedValue(generator = "seq_posts_id")
//    @SequenceGenerator(name = "seq_posts_id", sequenceName = "seq_posts_id")
//    @Column(name = "ID")
//    @NotNull //javax
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(name = "title")
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

//    @OneToMany
//    @JoinColumn(name = "CREATOR_ID", refferencedColumnName = "ID")
//    @NotNull
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

//    @OneToOne
//    @JoinColumn(name = "AVERAGE_RATER_ID", refferencedColumnName = "ID")
    public Double getAveragePostRate() {
        updateAverageRater();
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
        updateAverageRater();
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
        updateAverageRater();
    }

    public void addSerialRaters(ArrayList<SerialRater> serialRaters)
    {
        this.serialRaters.addAll(serialRaters);
        updateAverageRater();
    }

    public void updateAverageRater()
    {
        final int FIRST_RATER_INDEX = 0;
        final int SECOND_RATER_INDEX = 1;
        final int THIRD_RATER_INDEX = 2;

        int[] counters = {0, 0, 0};
        int[] sums = {0, 0, 0};
        for(int i = 0; i < serialRaters.size(); i++)
        {
            SerialRater currentSerialRater = serialRaters.get(i);
            if((currentSerialRater.poemRateSections != null && currentSerialRater.poemRateSections.equals(PoemRateSections.DEPT)) ||
                    (currentSerialRater.melodyRateSections != null && currentSerialRater.melodyRateSections.equals(MelodyRateSections.RHYTHM)))
            {
                counters[FIRST_RATER_INDEX]++;
                sums[FIRST_RATER_INDEX]+=currentSerialRater.getValue();
            }
            else if((currentSerialRater.poemRateSections != null && currentSerialRater.poemRateSections.equals(PoemRateSections.RHYMES)) ||
                    (currentSerialRater.melodyRateSections != null && currentSerialRater.melodyRateSections.equals(MelodyRateSections.QUALITY)))
            {
                counters[SECOND_RATER_INDEX]++;
                sums[SECOND_RATER_INDEX]+=currentSerialRater.getValue();
            }

            else if((currentSerialRater.poemRateSections != null && currentSerialRater.poemRateSections.equals(PoemRateSections.LANGUAGE)) ||
                    (currentSerialRater.melodyRateSections != null && currentSerialRater.melodyRateSections.equals(MelodyRateSections.UNIQUENESS)))
            {
                counters[THIRD_RATER_INDEX]++;
                sums[THIRD_RATER_INDEX]+=currentSerialRater.getValue();
            }
        }

        if (sums[FIRST_RATER_INDEX] > 0) {
            averageRater.setRaterSection1((double) (sums[FIRST_RATER_INDEX]/counters[FIRST_RATER_INDEX]));
        }
        if (sums[SECOND_RATER_INDEX] > 0) {
            averageRater.setRaterSection2((double) (sums[SECOND_RATER_INDEX]/counters[SECOND_RATER_INDEX]));
        }
        if (sums[THIRD_RATER_INDEX] > 0) {
            averageRater.setRaterSection3((double) (sums[THIRD_RATER_INDEX]/counters[THIRD_RATER_INDEX]));
        }
        updateAverageRate();

    }


    public void updateAverageRate() {
        double sum = 0;
        double counter = 0;
        if (this.getAverageRater().getRaterSection1() > 0) {
            sum += this.getAverageRater().getRaterSection1();
            counter++;
        }
        if (this.getAverageRater().getRaterSection2() > 0) {
            sum += this.getAverageRater().getRaterSection2();
            counter++;
        }
        if (this.getAverageRater().getRaterSection3() > 0) {
            sum += this.getAverageRater().getRaterSection3();
            counter++;
        }

        if (sum > 0) {
            this.setAveragePostRate(round((sum/counter),1));
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public AverageRater getAverageRater() {
        return averageRater;
    }
}
