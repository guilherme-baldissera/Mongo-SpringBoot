package com.daitangroup.mysql.daos;

import com.daitangroup.mysql.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findAllByGroupId(String groupId);
}
