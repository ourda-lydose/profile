package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Async
    public CompletableFuture<UserProfile> create(UserProfile user){
        profileRepository.save(user);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    @Async
    public CompletableFuture<List<UserProfile>> findAll(){
        return CompletableFuture.completedFuture(profileRepository.findAll());
    }

    @Override
    @Async
    public CompletableFuture<UserProfile> edit(UserProfile user){
        profileRepository.save(user);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    @Async
    public CompletableFuture<Void> delete(String id){
        profileRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Optional<UserProfile>> findById(String id){
        return CompletableFuture.completedFuture(profileRepository.findById(id));
    }
}
