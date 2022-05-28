package com.example.musimatch.models;

import androidx.room.Entity;

import com.example.musimatch.models.enums.TagGroup;

@Entity
public class Tag {
    private Long id;
    private TagGroup tagGroup = TagGroup.NONE;
    private String name;

    public Tag(){}

    public Tag(Long id, TagGroup tagGroup, String name) {
        this.id = id;
        this.tagGroup = tagGroup;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagGroup getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroup tagGroup) {
        this.tagGroup = tagGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
