package com.example.musimatch.services;

import com.example.musimatch.server.api.UserServiceInterface;
import com.example.musimatch.server.ejb.UserServiceEJB;

public class MusimatchServices {
    public static MusimatchServices instance = new MusimatchServices();

    public UserServiceInterface getUserService()
    {
        return UserServiceEJB.instance;
    }
}
