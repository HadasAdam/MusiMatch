package com.example.musimatch.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.musimatch.models.enums.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserModel {
    ArrayList<User> users = new ArrayList<>();

    public final static UserModel instance = new UserModel();

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    public User findUserById(Long id) {
        for (User user : users)
        {
            if(user.getId() == id)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeUsersList()
    {
        for(int i = 0; i < 10; i++)
        {
            users.add(new User((long)i,"user" + i, false, LocalDate.now(), UserType.POET, "john" + i,
                    "Adams" + i, 0d, ""));
        }
    }
}
