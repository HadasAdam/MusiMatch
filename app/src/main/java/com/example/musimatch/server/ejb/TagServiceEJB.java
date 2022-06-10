package com.example.musimatch.server.ejb;

import com.example.musimatch.models.Tag;
import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;

public class TagServiceEJB {
    public static final TagServiceEJB instance = new TagServiceEJB();
    private static final GeneralMusimatchDaoLocal daoService = GeneralMusimatchDaoEJB.instance;
    public Tag getTagById(Long id)
    {
        return daoService.findById(Tag.class, id);
    }
}
