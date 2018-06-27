package com.daitangroup.mysql.services;

import com.daitangroup.mysql.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    User getUser(String id);

    List<User> getAllUsers();

    List<User> getAllUsersSortedByName();

    User getUserByName(String name);
}
