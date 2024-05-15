package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProfileServiceImpl implements  ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Async
    public CompletableFuture<User> create(User user){
        profileRepository.save(user);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    @Async
    public CompletableFuture<List<User>> findAll(){
        return CompletableFuture.completedFuture(profileRepository.findAll());
    }

    @Override
    @Async
    public CompletableFuture<User> edit(User user){
        profileRepository.save(user);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    public CompletableFuture<Void> delete(int id){
        profileRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Optional<User>> findById(int id){
        return CompletableFuture.completedFuture(profileRepository.findById(id));
    }
}
