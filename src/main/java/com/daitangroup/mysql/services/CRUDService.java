package com.daitangroup.mysql.services;

import com.daitangroup.mysql.entities.User;

public interface CRUDService {
    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    User getUser(String id);
}
