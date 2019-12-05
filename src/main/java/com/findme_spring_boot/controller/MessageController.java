package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.model.oracle.Message;
import com.findme_spring_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(path = "/message/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody Message message) throws Exception {
        messageService.save(message);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/message/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody Message message) throws Exception {
        messageService.update(message);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/message/delete/{messageId}")
    public ResponseEntity<String> delete(@PathVariable String messageId) throws Exception {
        messageService.delete(messageId);
        return new ResponseEntity<>("message deleted", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message/get/{messageId}", produces = "text/plain")
    public @ResponseBody
    String get(Model model, @PathVariable String messageId) throws Exception {
        model.addAttribute("message", messageService.findById(messageService.parseMessageId(messageId)));
        return "message";
    }
}
