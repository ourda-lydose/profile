package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfileRepository implements ProfileRepositoryInterface{
    private List<User> userList = new ArrayList<>();

    public User create(User user) {
        User existingUser = findById(user.getId());

        if (existingUser != null) {
            int index = userList.indexOf(existingUser);
            userList.set(index, user);
        } else {
            userList.add(user);
        }

        return user;
    }

    public User delete(User User){
        userList.remove(User);
        return User;
    }

    public User findById(int id){
        for(User savedUser : userList){
            if(savedUser.getId().equals(id)){
                return savedUser;
            }
        }
        return null;
    }


    public User edit(User updatedUser){
        for (int i = 0; i < userList.size(); i++) {
            User User = userList.get(i);
            if(User.getId().equals(updatedUser.getId())){
                userList.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    public List<User> findAll(){
        return userList;
    }
}
