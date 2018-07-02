package com.daitangroup.mysql.steps;

import com.daitangroup.mysql.context.ThreadVariableWrapper;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.entities.User;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GroupSteps extends Steps {

    private static final String URL_GROUPS = "http://localhost:8080/groups";
    private RestTemplate restTemplate;

    public GroupSteps() {
        this.restTemplate = new RestTemplate();
    }

    @Given("the new group $group of 3 user(s) $users created by user $userOwner")
    public void createGroups(@Named("$group") String group, @Named("users") List<String> users, @Named("userOwner") String userOwner){


        HttpEntity<Group> request;
        ResponseEntity<Group> response;
        Group body;

        List<String> guestsId = new ArrayList<>();
        String userOnwerId = ((User) ThreadVariableWrapper.getContext().getVariables().get(userOwner)).getId();

        users.forEach(user ->{
            guestsId.add(((User) ThreadVariableWrapper.getContext().getVariables().get(user)).getId());
        } );

        body = new Group(userOnwerId, guestsId, "Group Name");
        request = new HttpEntity<>(body);
        response = restTemplate.postForEntity(URL_GROUPS, request, Group.class);

        Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assert.assertEquals(Group.class, response.getBody().getClass());

        ThreadVariableWrapper.getContext().getVariables().put(group, response.getBody());
    }
}
