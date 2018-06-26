package com.daitangroup.mysql.Daos;

import com.daitangroup.mysql.entities.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group,String> {

    Group getGroupByName(String Name);
}
