package com.daitangroup.mysql.daos;

import com.daitangroup.mysql.entities.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group,String> {
    List<Group> findAllByUsersIdGuestsIn(String userId);
}
