package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.GroupRepository;
import com.daitangroup.mysql.daos.UserRepository;
import com.daitangroup.mysql.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CRUDUserService crudUserService;

    @Override
    public Group addGroup(Group group) {
        //verifying if Users exist in DB
        crudUserService.getUser(group.getUserAdmin().getId());
        group.getGuests().stream().forEach(guest -> crudUserService.getUser(guest.getId()));

        group.getGuests().add(group.getUserAdmin());

        return groupRepository.save(group);
    }
}
