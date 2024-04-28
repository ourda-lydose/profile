package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;

import java.util.Iterator;

public interface ProfileRepositoryInterface {
    User create(User user);

    User delete(User user);

    User edit(User updatedUser);

    User findById(int id);

    Iterator<User> findAll();
}