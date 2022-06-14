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
        tags.add(new Tag(0L, TagGroup.HAPPINESS, "Joy"));
        tags.add(new Tag(1L, TagGroup.ROMANCE, "Love"));
        tags.add(new Tag(2L, TagGroup.SADNESS, "Sadness"));
        tags.add(new Tag(3L, TagGroup.SADNESS, "Pain"));
        tags.add(new Tag(4L, TagGroup.ANIMALS, "Cats"));
        tags.add(new Tag(5L, TagGroup.ANIMALS, "Dogs"));
        tags.add(new Tag(6L, TagGroup.HAPPINESS, "Family"));
        tags.add(new Tag(7L, TagGroup.HAPPINESS, "Life"));
        tags.add(new Tag(8L, TagGroup.SADNESS, "Depression"));
        tags.add(new Tag(9L, TagGroup.SADNESS, "Struggles"));
        tags.add(new Tag(10L, TagGroup.KIDS, "Childhood"));
        tags.add(new Tag(11L, TagGroup.HAPPINESS, "Friends"));
        tags.add(new Tag(12L, TagGroup.ROMANCE, "Kisses"));
        tags.add(new Tag(13L, TagGroup.ANIMALS, "Fish"));
    }
}
