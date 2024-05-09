package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.List;
import java.util.Optional;


public interface ProfileService {
    public User create(User user);
    public List<User> findAll();
    public User edit(User user);
    public Optional<User> findById(int id);
    public void delete(User user);
}
