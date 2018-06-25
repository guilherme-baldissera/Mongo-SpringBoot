package com.daitagroup.mysql.services;

import com.daitagroup.mysql.entities.User;

public interface CRUDService {
    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    User getUser(Integer id);
}
