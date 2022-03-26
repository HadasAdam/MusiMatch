package com.example.musimatch.models;

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

    private void initializeUsersList()
    {
        for(int i = 0; i < 10; i++)
        {
            users.add(new User(String.valueOf(i),"user" + i, false, 0d, UserType.POET, "john" + i,
                    "Adams" + i, 0d, ""));
        }
    }
}
