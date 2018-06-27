package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.GroupRepository;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.exception.UserIdMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupServiceImp implements GroupService {

    private static String USER_ID_MISSED = "User id is missed";

    GroupRepository groupRepository;

    UserService crudUserService;

    @Autowired
    public GroupServiceImp(GroupRepository groupRepository, UserService crudUserService) {
        this.groupRepository = groupRepository;
        this.crudUserService = crudUserService;
    }

    @Override
    public Group addGroup(Group group) {
        if(group.getUserAdmin().getId() == null || group.getUserAdmin().getId().isEmpty())
            throw new UserIdMissingException(USER_ID_MISSED);

        crudUserService.getUser(group.getUserAdmin().getId());
        group.getGuests().stream().forEach(guest -> crudUserService.getUser(guest.getId()));

        group.getGuests().add(group.getUserAdmin());

        return groupRepository.save(group);
    }
}
