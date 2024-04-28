package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements  ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User create(User user){
        if (profileRepository.findById(user.getId()) == null) {
            profileRepository.create(user);
            return user;
        }
        return null;
    }

    @Override
    public List<User> findAll(){
        return profileRepository.findAll();
    }

    @Override
    public User edit(User user){
        if (profileRepository.findById(user.getId()) != null) {
            profileRepository.edit(user);
            return user;
        }
        return null;
    }

    @Override
    public User delete(User user){
        if (profileRepository.findById(user.getId()) != null) {
            profileRepository.delete(user);
            return user;
        }
        return null;
    }

    @Override
    public User findById(int id){
        return profileRepository.findById(id);
    }
}
