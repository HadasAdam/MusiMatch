package com.example.musimatch.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.musimatch.models.Post;
import com.example.musimatch.models.Tag;
import com.example.musimatch.models.enums.TagGroup;
import com.example.musimatch.models.TagModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TagRecommendationService {
    private static TagRecommendationService tagRecommendationService;
    private ArrayList<Tag> userTags;

    public ArrayList<Tag> getUserTags() {
        return userTags;
    }

    public void setUserTags(ArrayList<Tag> userTags) {
        this.userTags = userTags;
    }

    public void setUserTagsByPosts(ArrayList<Post> userPosts) {
        for(int i = 0; i < userPosts.size(); i++)
        {
            ArrayList<Tag> currentPostTags = userPosts.get(i).getTags();
            for(int j = 0; j < currentPostTags.size(); j++)
            {
                userTags.addAll(currentPostTags);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Tag> getRecommendedTags()
    {
        ArrayList<Tag> allTags = TagModel.instance.getAllTags();
        TagGroup commonTagGroup = getCommonTagGroup();
        ArrayList<Tag> tagsOfTagGroup = new ArrayList<>();

        for(int i = 0; i < allTags.size(); i++)
        {
            if(allTags.get(i).getTagGroup().equals(commonTagGroup))
            {
                tagsOfTagGroup.add(allTags.get(i));
            }
        }
        return tagsOfTagGroup;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private TagGroup getCommonTagGroup()
    {
        HashMap<TagGroup, Integer> tagGroupMap = createHashMapForTagGroups();
        return findMaxEnteryValue(tagGroupMap);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private HashMap<TagGroup, Integer> createHashMapForTagGroups()
    {
        HashMap<TagGroup, Integer> tagGroupMap = new HashMap<>();
        for(int i = 0; i < userTags.size(); i++)
        {
            tagGroupMap.merge(userTags.get(i).getTagGroup(), 1, Integer::sum);
        }
        return tagGroupMap;
    }

    private TagGroup findMaxEnteryValue(HashMap<TagGroup, Integer> tagGroupMap)
    {
        Map.Entry<TagGroup, Integer> maxEntry = null;
        for (Map.Entry<TagGroup, Integer> entry : tagGroupMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
