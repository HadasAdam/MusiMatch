package com.example.musimatch.server.ejb;

import com.example.musimatch.models.Tag;
import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.server.api.TagServiceInterface;
import com.example.musimatch.server.api.TagServiceLocal;

import java.util.List;

public class TagServiceEJB implements TagServiceInterface, TagServiceLocal {
    public static final TagServiceEJB instance = new TagServiceEJB();
    private static final GeneralMusimatchDaoLocal daoService = GeneralMusimatchDaoEJB.instance;
    @Override
    public Tag getTagById(Long id)
    {
        return (Tag) daoService.findById(Tag.getTableName(), id);
    }

    @Override
    public List<Tag> getAllTags()
    {
        return (List<Tag>) daoService.findAll(Tag.getTableName());
    }
}
