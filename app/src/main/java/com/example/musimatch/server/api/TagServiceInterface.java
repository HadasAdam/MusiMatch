package com.example.musimatch.server.api;

import com.example.musimatch.models.Tag;

import java.util.List;

public interface TagServiceInterface {
    Tag getTagById(Long id);
    List<Tag> getAllTags();
}
