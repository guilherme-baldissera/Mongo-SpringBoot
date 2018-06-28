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

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Group addGroup(@Valid @RequestBody Group group){
        return groupService.addGroup(group);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Group getGroup(@PathVariable String id){
        return groupService.getGroup(id);
    }

}
