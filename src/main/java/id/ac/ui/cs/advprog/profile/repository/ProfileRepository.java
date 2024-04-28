package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ProfileRepository implements ProfileRepositoryInterface{
    private List<User> userData = new ArrayList<>();
    private UserFinder userFinder = new UserFinder();

    public User create(User user){
        userData.add(user);
        return user;
    }

    public User delete(User User){
        userData.remove(User);
        return User;
    }

    public User findById(int id){
        return userFinder.findById(id, userData);
    }


    public User edit(User updatedUser){
        for (int i = 0; i < userData.size(); i++) {
            User User = userData.get(i);
            if(User.getId().equals(updatedUser.getId())){
                userData.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    public Iterator<User> findAll(){
        return UserFinder.findAll(userData);
    }

}
