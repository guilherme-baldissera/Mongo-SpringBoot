package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.MessageRepository;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.entities.Message;
import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.exception.UserNotPartOfGroupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    private static String USER_NOT_IN_GROUP = "User is not part of the group";


    private MessageRepository messageRepository;
    private GroupService groupService;
    private UserService userService;

    @Autowired
    public MessageServiceImp(MessageRepository messageRepository, GroupService groupService, UserService userService) {
        this.messageRepository = messageRepository;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    public Message sendMessage(Message message) {
        User user =userService.getUser(message.getUserIdWhoSent());
        Group group = groupService.getGroup(message.getGroupId());

        if(!group.getUsersIdGuests().contains(user.getId()))
            throw new UserNotPartOfGroupException(USER_NOT_IN_GROUP);

        message.setTime(Instant.now());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesFromGroup(String groupId) {
        groupService.getGroup(groupId);

        List<Message> messages = messageRepository.findAllByGroupId(groupId);

        return messages;
    }

    @Override
    public List<Message> getAllMessagesFromUser(String userId) {
        List<Group> groups = groupService.getAllGroupsFromUser(userId);

        List<Message> messages = new ArrayList<>();

        groups.stream().forEach(group -> {
            messages.addAll(getMessagesFromGroup(group.getId()));
        });

        return messages;
    }


}
