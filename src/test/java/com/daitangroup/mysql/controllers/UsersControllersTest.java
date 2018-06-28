package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.services.UserService;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class UsersControllersTest {

    private UserService userService;

    @Before
    public void init(){
        userService = Mockito.mock(UserService.class);
    }

    @Test
    public void testAddUser(){
        UserController userController = new UserController(userService);
        User user = new User("João Paulo","123456");

        when(userService.addUser(ArgumentMatchers.any(User.class))).thenReturn(user);

        Assert.assertEquals(User.class,userController.addUser(user).getClass());

        verify(userService,times(1)).addUser(user);
    }
}
