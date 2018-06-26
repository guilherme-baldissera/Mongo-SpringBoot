package com.daitangroup.mysql.Daos;

import com.daitangroup.mysql.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name);
}
