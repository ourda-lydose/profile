package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ProfileService {
    public CompletableFuture<User> create(User user);
    public CompletableFuture<List<User>> findAll();
    public CompletableFuture<User> edit(User user);
    public CompletableFuture<Optional<User>> findById(int id);
    public CompletableFuture<Void> delete(int id);
}
