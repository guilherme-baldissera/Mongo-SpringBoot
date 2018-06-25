package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {

    @Autowired
    private CRUDService crudService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user){
        return crudService.addUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUser(@PathVariable Integer id){
        return crudService.getUser(id);
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
