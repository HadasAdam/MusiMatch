package com.example.musimatch.server.ejb;

import com.example.musimatch.models.User;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.server.api.UserServiceInterface;
import com.example.musimatch.server.api.UserServiceLocal;

import java.util.ArrayList;

//import javax.ejb.EJB;
//import javax.ejb.Local;
//import javax.ejb.Remote;
//import javax.ejb.Stateless;
//import javax.ejb.Interceptors;
//
//@Remote(UserServiceInterface.class)
//@Local(UserServiceLocal.class)
//@Stateless(mappedName = "UserService")
public class UserServiceEJB implements UserServiceInterface {

    //@EJB
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
