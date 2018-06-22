package com.daitagroup.mysql.services;

import com.daitagroup.mysql.entities.User;

public interface CRUDService {
    void addOrUpdateUser(User user);

    void deleteUser(User user);

    User getUser(Integer id);
}
