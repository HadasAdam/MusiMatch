package com.example.musimatch.services;

import com.example.musimatch.server.api.GeneralMusimatchDaoInterface;
import com.example.musimatch.server.api.PostServiceInterface;
import com.example.musimatch.server.api.TagServiceInterface;
import com.example.musimatch.server.api.UserServiceInterface;
import com.example.musimatch.server.ejb.GeneralMusimatchDaoEJB;
import com.example.musimatch.server.ejb.PostServiceEJB;
import com.example.musimatch.server.ejb.TagServiceEJB;
import com.example.musimatch.server.ejb.UserServiceEJB;

public class MusimatchServices {
    public static MusimatchServices instance = new MusimatchServices();

    public UserServiceInterface getUserService()
    {
        return UserServiceEJB.instance;
    }

    public PostServiceInterface getPostService()
    {
        return PostServiceEJB.instance;
    }

    public TagServiceInterface getTagService()
    {
        return TagServiceEJB.instance;
    }

    public GeneralMusimatchDaoInterface getGeneralService()
    {
        return GeneralMusimatchDaoEJB.instance;
    }
}
