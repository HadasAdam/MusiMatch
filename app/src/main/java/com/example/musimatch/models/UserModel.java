package com.example.musimatch.models;

import com.example.musimatch.models.enums.UserType;

import java.util.ArrayList;

public class UserModel {
    ArrayList<User> users = new ArrayList<>();

    public final static UserModel instance = new UserModel();

    private UserModel() {
        initializeUsersList();
    }

    public ArrayList<User> getAllUsers()
    {
        return users;
    }

    public void createUser(User user) {
        users.add(user);
    }

    public User findUserById(String id) {
        for (User user : users)
        {
            if(user.getId().equals(id))
            {
                return user;
            }
        }
        return null;
    }

    public User findUserByUsername(String username) {
        for (User user : users)
        {
            if(user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

    private void initializeUsersList()
    {
        for(int i = 0; i < 10; i++)
        {
            users.add(new User((long)i,"user" + i, false, 0d, UserType.POET, "john" + i,
                    "Adams" + i, 0d, ""));
        }
    }
}
