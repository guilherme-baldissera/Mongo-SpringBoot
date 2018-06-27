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

    private UserService crudService;

    @Autowired
    public UserController(UserService crudService) {
        this.crudService = crudService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user){
        return crudService.addUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUser(@PathVariable String id){
        return crudService.getUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> getAllUsers(){
        return crudService.getAllUsersSortedByName();
    }

    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUserByName(@RequestParam String name){
        return crudService.getUserByName(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user){
        return crudService.updateUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Valid @RequestBody User user){
        crudService.deleteUser(user);
    }
}
