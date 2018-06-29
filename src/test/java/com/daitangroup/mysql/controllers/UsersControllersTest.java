package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.services.UserService;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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

        verify(userService,times(1)).addUser(ArgumentMatchers.any(User.class));
    }

    @Test
    public void testGetUser(){
        UserController userController = new UserController(userService);
        User user = new User("João Paulo","123456");

        when(userService.getUser(ArgumentMatchers.any(String.class))).thenReturn(user);

        Assert.assertEquals(User.class,userController.getUser("1").getClass());

        verify(userService,times(1)).getUser(ArgumentMatchers.any(String.class));
    }

    @Test
    public void testGetAllUsers(){
        UserController userController = new UserController(userService);
        List<User> users = new ArrayList();

        when(userService.getAllUsersSortedByName()).thenReturn(users);

        Assert.assertEquals(ArrayList.class,userController.getAllUsers().getClass());

        verify(userService,times(1)).getAllUsersSortedByName();
    }

    @Test
    public void testGetUserByName(){
        UserController userController = new UserController(userService);
        User user = new User("João Paulo","123456");

        when(userService.getUserByName(ArgumentMatchers.any(String.class))).thenReturn(user);

        Assert.assertEquals(User.class,userController.getUserByName(user.getName()).getClass());

        verify(userService,times(1)).getUserByName(user.getName());
    }

    @Test
    public void testUpdateUser(){
        UserController userController = new UserController(userService);
        User user = new User("João Paulo","123456");

        when(userService.updateUser(ArgumentMatchers.any(User.class))).thenReturn(user);

        Assert.assertEquals(User.class,userController.updateUser(user).getClass());

        verify(userService,times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        UserController userController = new UserController(userService);
        User user = new User("João Paulo","123456");

        userController.deleteUser(user);
        verify(userService,times(1)).deleteUser(user);
    }

}
