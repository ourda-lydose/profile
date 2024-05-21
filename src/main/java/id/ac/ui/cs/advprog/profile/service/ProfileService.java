package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ProfileService {
    public CompletableFuture<UserProfile> create(UserProfile user);
    public CompletableFuture<List<UserProfile>> findAll();
    public CompletableFuture<UserProfile> edit(UserProfile user);
    public CompletableFuture<Optional<UserProfile>> findById(String id);
    public CompletableFuture<Void> delete(String id);
}
