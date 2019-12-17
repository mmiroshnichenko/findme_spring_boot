package com.findme_spring_boot.controller.api;

import com.findme_spring_boot.model.oracle.Message;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ApiMessageController {
    private MessageService messageService;

    @Autowired
    public ApiMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message/save")
    public Message save(@RequestBody Message message, HttpSession session) throws Exception {
        return messageService.save(message, (User) session.getAttribute("USER"));
    }

    @PutMapping("/message/update")
    public Message update(@RequestBody Message message, HttpSession session) throws Exception {
        return messageService.update(message, (User) session.getAttribute("USER"));
    }

    @DeleteMapping(value = "/message/delete/{messageId}")
    public void delete(@PathVariable String messageId, HttpSession session) throws Exception {
        messageService.delete(messageId, (User) session.getAttribute("USER"));
    }

    @GetMapping(value = "/messages/count")
    public int getCount(HttpSession session) throws Exception {
        return messageService.getCountIncomeMessages((User) session.getAttribute("USER"));
    }

    @DeleteMapping(value = "/messages/user/{userId}")
    public void deleteMessagesWithUser(@PathVariable String userId, HttpSession session) throws Exception {
        messageService.deleteMessagesWithUser(userId, (User) session.getAttribute("USER"));
    }

    @DeleteMapping(value = "/messages")
    public void deleteSomeMessages(@RequestParam(value="messageIds[]") String[] messageIds, HttpSession session) throws Exception {
        messageService.deleteSomeMessages(messageIds, (User) session.getAttribute("USER"));
    }
}
