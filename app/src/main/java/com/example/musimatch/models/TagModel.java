package com.example.musimatch.models;

import com.example.musimatch.models.enums.TagGroup;

import java.util.ArrayList;

public class TagModel {
    ArrayList<Tag> tags = new ArrayList<>();

    public final static TagModel instance = new TagModel();

    private TagModel() {
        initializeTagsList();
    }

    public ArrayList<Tag> getAllTags()
    {
        return tags;
    }

    public Tag findTagById(Long id) {
        for (Tag tag : tags)
        {
            if(tag.getId() == (long)id)
            {
                return tag;
            }
        }
        return null;
    }

    public Tag findTagByName(String tagName)
    {
        for(Tag tag: tags)
        {
            if(tag.getName().equals(tagName))
            {
                return tag;
            }
        }
        return null;
    }

    private void initializeTagsList()
    {
        for (int i = 0; i < 10; i++)
        {
            tags.add(new Tag((long)i, TagGroup.HAPPINESS, "Happy" + i));
            tags.add(new Tag((long)(i*10), TagGroup.ROMANCE, "Romance" + i));
            tags.add(new Tag((long)(i*100), TagGroup.SADNESS, "Sadness" + i));
        }
    }
}
