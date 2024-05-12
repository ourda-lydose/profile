package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProfileServiceImpl implements  ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User create(User user){
        profileRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll(){
        return profileRepository.findAll();
    }

    @Override
    public User edit(User user){
        profileRepository.save(user);
        return user;
    }

    @Override
    public void delete(int id){
        profileRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(int id){
        return profileRepository.findById(id);
    }
}
