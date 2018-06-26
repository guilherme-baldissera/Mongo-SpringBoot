package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.services.CRUDUserService;
import com.daitangroup.mysql.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    CRUDUserService crudUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group addGroup(@Valid @RequestBody Group group){
        return groupService.addGroup(group);
    }

}
