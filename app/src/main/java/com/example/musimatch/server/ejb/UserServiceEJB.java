package com.example.musimatch.server.ejb;

import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.server.api.UserServiceInterface;

import java.util.ArrayList;

public class UserServiceEJB implements UserServiceInterface {
    public static final UserServiceEJB instance = new UserServiceEJB();
    private static final GeneralMusimatchDaoLocal daoService = GeneralMusimatchDaoEJB.instance;

    @Override
    public User getUserById(Long id) {
        return (User) daoService.findById(User.getTableName(), id);
    }

    @Override
    public User getUserByUsername(String username) {
        ArrayList<User> users = (ArrayList<User>) daoService.findAll(User.getTableName());
        for(User user: users)
        {
            if(user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        daoService.merge(user);
    }
}
