package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.GroupRepository;
import com.daitangroup.mysql.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImp implements GroupService {

    private GroupRepository groupRepository;

    private UserService crudUserService;

    @Autowired
    public GroupServiceImp(GroupRepository groupRepository, UserService crudUserService) {
        this.groupRepository = groupRepository;
        this.crudUserService = crudUserService;
    }
    @Override
    public Group addGroup(Group group) {

        return groupRepository.save(group);
    }
}
