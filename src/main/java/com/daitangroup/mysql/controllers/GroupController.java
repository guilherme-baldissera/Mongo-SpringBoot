package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.services.UserService;
import com.daitangroup.mysql.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("groups")
public class GroupController {

    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group addGroup(@Valid @RequestBody Group group){
        return groupService.addGroup(group);
    }
}
