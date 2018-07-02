package com.daitangroup.mysql.steps;

import com.daitangroup.mysql.context.ThreadVariableWrapper;
import com.daitangroup.mysql.entities.Group;
import com.daitangroup.mysql.entities.Message;
import com.daitangroup.mysql.entities.User;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

public class MessageSteps extends Steps {
    private static final String URL_MESSAGES = "http://localhost:8080/messages";
    private RestTemplate restTemplate;

    public MessageSteps() {
        this.restTemplate = new RestTemplate();
    }

    @When("the user $user sends a message $message for group $group")
    public void createMessages(String user, String message, String group){
        HttpEntity<Message> request;
        ResponseEntity<Message> response;
        Message body;

        String groupId = ((Group) ThreadVariableWrapper.getContext().getVariables().get(group)).getId();
        String userId = ((User) ThreadVariableWrapper.getContext().getVariables().get(user)).getId();

        body = new Message("Message Body", Instant.now(), groupId, userId);
        request = new HttpEntity<>(body);
        response = restTemplate.postForEntity(URL_MESSAGES, request, Message.class);

        Assert.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Assert.assertEquals(Message.class, response.getBody().getClass());

        ThreadVariableWrapper.getContext().getVariables().put(message, response.getBody());
    }
}
