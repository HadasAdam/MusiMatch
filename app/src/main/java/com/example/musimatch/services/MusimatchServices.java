package com.example.musimatch.services;

import com.example.musimatch.server.api.UserServiceInterface;
import com.example.musimatch.server.ejb.UserServiceEJB;

public class MusimatchServices {
    private static MusimatchServices instance = new MusimatchServices();

    public static UserServiceInterface getUserService()
    {
        return new UserServiceEJB();
    }
}
