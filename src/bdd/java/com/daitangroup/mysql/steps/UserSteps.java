package com.daitangroup.mysql.steps;

import com.daitangroup.mysql.context.ThreadVariableWrapper;
import com.daitangroup.mysql.entities.Message;
import com.daitangroup.mysql.entities.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSteps extends Steps {

    private static final String URL_USERS = "http://localhost:8080/users";
    private static final String URL_MESSAGES = "http://localhost:8080/messages/{userId}";

    private RestTemplate restTemplate;

    public UserSteps() {
        this.restTemplate = new RestTemplate();
    }

    @Given("the new user(s) $user")
    public void createUsers(@Named("user") List<String> users){

        HttpEntity<User> request;
        ResponseEntity<User> response;
        User body;

        for(String user : users){
            body = new User(RandomStringUtils.randomAlphabetic(10),RandomStringUtils.randomAlphanumeric(10));
            request = new HttpEntity<>(body);
            response = restTemplate.postForEntity(URL_USERS, request, User.class);

            Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
            Assert.assertEquals(User.class, response.getBody().getClass());

            ThreadVariableWrapper.getContext().getVariables().put(user, response.getBody());
        }
    }

    @Then("the users $user of the group $group should be able to read the message $message")
    public void getMessages(@Named("users") List<String> users, @Named("group") String group, @Named("message") String message){
        HttpEntity<String> request;
        ResponseEntity<Message[]> response;

        String userId = ((User) ThreadVariableWrapper.getContext().getVariables().get(users.get(1))).getId();

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        response = restTemplate.getForEntity(URL_MESSAGES, Message[].class, params);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());

        Assert.assertEquals(((Message) ThreadVariableWrapper.getContext().getVariables().get(message)).getId(), response.getBody()[0].getId());

    }
}