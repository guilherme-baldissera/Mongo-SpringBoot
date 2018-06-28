package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addUser(@Valid @RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> getAllUsers(){
        return userService.getAllUsersSortedByName();
    }

    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUserByName(@RequestParam String name){
        return userService.getUserByName(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User updateUser(@Valid @RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Valid @RequestBody User user){
        userService.deleteUser(user);
    }
}
