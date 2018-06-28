package com.daitangroup.mysql.services;

import com.daitangroup.mysql.entities.Group;

import java.util.List;

public interface GroupService {

    Group addGroup(Group group);
    Group getGroup(String id);
    List<Group> getAllGroupsFromUser(String userId);
}
