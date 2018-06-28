package com.daitangroup.mysql.services;

import com.daitangroup.mysql.entities.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);

    List<Message> getMessagesFromGroup(String groupId);

    List<Message> getAllMessagesFromUser(String userId);
}
