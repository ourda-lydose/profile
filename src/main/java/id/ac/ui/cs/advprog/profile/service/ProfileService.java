package id.ac.ui.cs.advprog.profile.service;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.List;

public interface ProfileService {
    public User create(User user);
    public List<User> findAll();
    public User edit(User user);
    public User findById(int id);
    public User delete(User user);
}
