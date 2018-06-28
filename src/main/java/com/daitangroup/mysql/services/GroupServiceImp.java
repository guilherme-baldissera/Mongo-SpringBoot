package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.GroupRepository;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.exception.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImp implements GroupService {

    private static String GROUP_NOT_FOUND = "Group not found";


    private GroupRepository groupRepository;

    private UserService userService;

    @Autowired
    public GroupServiceImp(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }
    @Override
    public Group addGroup(Group group) {
        userService.getUser(group.getUserIdOwner());
        group.getUsersIdGuests().stream().forEach(userId -> userService.getUser(userId));

        group.getUsersIdGuests().add(group.getUserIdOwner());

        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(String id) {
        Optional<Group> groupOptional = groupRepository.findById(id);

        if(!groupOptional.isPresent())
            throw new GroupNotFoundException(GROUP_NOT_FOUND);

        return groupOptional.get();
    }

    @Override
    public List<Group> getAllGroupsFromUser(String userId) {
        userService.getUser(userId);
        return groupRepository.findAllByUsersIdGuestsIn(userId);
    }
}
