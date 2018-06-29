package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.MessageRepository;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.entities.Message;
import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.exception.UserNotPartOfGroupException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


public class MessageServiceTest {

    private MessageRepository messageRepository;
    private GroupService groupService;
    private UserService userService;

    @Before
    public void init(){
        messageRepository = Mockito.mock(MessageRepository.class);
        groupService = Mockito.mock(GroupService.class);
        userService = Mockito.mock(UserService.class);
    }

    @Test()
    public void testSendMessageSuccess(){
        MessageService messageService = new MessageServiceImp(messageRepository,groupService,userService);
        User user = new User("João Paulo","123456");
        user.setId("1");
        List<String> usersIds = new ArrayList<>();
        usersIds.add("1");
        Group group = new Group("1",usersIds,"groupName");

        Message message = new Message();
        message.setGroupId("1");
        message.setUserIdWhoSent("1");

        when(userService.getUser(ArgumentMatchers.anyString())).thenReturn(user);

        when(groupService.getGroup(ArgumentMatchers.anyString())).thenReturn(group);

        when(messageRepository.save(ArgumentMatchers.any(Message.class))).thenReturn(message);

        Assert.assertEquals(Message.class,messageService.sendMessage(message).getClass());
    }

    @Test(expected = UserNotPartOfGroupException.class)
    public void testSendMessageUserNotInGroup(){
        MessageService messageService = new MessageServiceImp(messageRepository,groupService,userService);
        User user = new User("João Paulo","123456");
        user.setId("1");
        List<String> usersIds = new ArrayList<>();
        usersIds.add("2");
        Group group = new Group("1",usersIds,"groupName");

        Message message = new Message();
        message.setGroupId("1");
        message.setUserIdWhoSent("1");

        when(userService.getUser(ArgumentMatchers.anyString())).thenReturn(user);

        when(groupService.getGroup(ArgumentMatchers.anyString())).thenReturn(group);

        when(messageRepository.save(ArgumentMatchers.any(Message.class))).thenReturn(message);

        messageService.sendMessage(message);
    }

    @Test
    public void testGetAllMessagesFromUser(){
        MessageService messageService = new MessageServiceImp(messageRepository,groupService,userService);

        List<Group> groups = new ArrayList<>();
        List<String> usersIds = new ArrayList<>();
        usersIds.add("1");
        Group group = new Group("1",usersIds,"groupName");
        group.setId("1");
        groups.add(group);

        List<Message> messages = new ArrayList<>();
        messages.add(new Message());

        when(groupService.getAllGroupsFromUser(ArgumentMatchers.anyString())).thenReturn(groups);

        when(messageRepository.findAllByGroupId(ArgumentMatchers.anyString())).thenReturn(messages);

        Assert.assertEquals(ArrayList.class, messageService.getAllMessagesFromUser("1").getClass());

        verify(messageRepository,times(1)).findAllByGroupId("1");
    }

}
