package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.Iterator;
import java.util.List;

public class UserFinder {
    public User findById(int id, List<User> userData){
        for(User user : userData){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public static Iterator<User> findAll(List<User> userData){
        return userData.iterator();
    }
}
