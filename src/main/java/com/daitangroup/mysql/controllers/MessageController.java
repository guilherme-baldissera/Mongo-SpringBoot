package com.daitangroup.mysql.controllers;

import com.daitangroup.mysql.entities.Message;
import com.daitangroup.mysql.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Message sendMessage(@Valid @RequestBody Message message){
        return messageService.sendMessage(message);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Message> getAllMessagesFromUserId(@PathVariable String userId){
        return messageService.getAllMessagesFromUser(userId);
    }
}
