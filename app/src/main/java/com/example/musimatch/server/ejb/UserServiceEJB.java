package com.example.musimatch.server.ejb;

import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.server.api.UserServiceInterface;

import java.util.ArrayList;

public class UserServiceEJB implements UserServiceInterface {

    private GeneralMusimatchDaoLocal daoService;

    @Override
    public User getUserById(Long id) {
        return daoService.findById(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        ArrayList<User> users = (ArrayList<User>) daoService.findAll(User.class);
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
