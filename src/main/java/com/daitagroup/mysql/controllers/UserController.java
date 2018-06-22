package com.daitagroup.mysql.controllers;

import com.daitagroup.mysql.entities.User;
import com.daitagroup.mysql.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {

    @Autowired
    private CRUDService crudService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        crudService.addOrUpdateUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User getUser(@PathVariable Integer id){
        return crudService.getUser(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user){
        crudService.addOrUpdateUser(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody User user){
        crudService.deleteUser(user);
    }

}
