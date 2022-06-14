package com.example.musimatch.models;

import android.os.Build;
import android.view.Display;

import androidx.annotation.RequiresApi;

import com.example.musimatch.models.enums.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

    public void removePost(User user)
    {
        if(users.contains(user))
        {
            users.remove(user);
        }
    }

    public void updateUser(User user)
    {
        for(User currentUser: users)
        {
            if(currentUser.getId().equals(user.getId()))
            {
                removePost(currentUser);
                users.add(user);
                return;
            }
        }
    }

    public void updateAverageRate(User user) {
        ArrayList<Post> allUserPosts = PostModel.instance.findPostsByUser(user);
        double sum = 0;
        double counter = 0;

        for (int i = 0; i < allUserPosts.size(); i++)
        {
            sum += allUserPosts.get(i).getAverageRater().getRaterSection1() +
                    allUserPosts.get(i).getAverageRater().getRaterSection2() +
                    allUserPosts.get(i).getAverageRater().getRaterSection3();
            counter += 3;
        }
        user.setAverageRate(round((sum/counter), 1));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeUsersList()
    {
        users.add(new User(0L,"AnakinSkywalker",  "darthvader@gmail.com", UserType.POET, "Anakin",
                "Skywalker"));
        users.add(new User(1L,"ObiWan67",  "starwars33@gmail.com", UserType.POET, "Obi Wan",
                "Kenobi"));
        users.add(new User(2L,"IcyHearted",  "ice123@gmail.com", UserType.POET, "John",
                "Williams"));
        users.add(new User(3L,"NoaBieber",  "bieber94@gmail.com", UserType.POET, "Noa",
                "Siman Tov"));
        users.add(new User(4L,"Venessa22",  "venessa2000@gmail.com", UserType.POET, "Venessa",
                "Adams"));
        users.add(new User(5L,"Lonely",  "imsolonely@gmail.com", UserType.POET, "Ben",
                "Avraham"));
        users.add(new User(6L,"LoveYou",  "loveIsCatchy@gmail.com", UserType.POET, "Nina",
                "Roberts"));
        users.add(new User(7L,"Kiki",  "kiki12@gmail.com", UserType.POET, "Drew",
                "Loren"));
        users.add(new User(8L,"GoogleMan",  "google11@gmail.com", UserType.POET, "Fred",
                "Van"));
        users.add(new User(9L,"JavaFan",  "java16@gmail.com", UserType.POET, "Shawn",
                "Suns"));
    }
}
