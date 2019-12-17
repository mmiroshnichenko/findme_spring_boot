package com.findme_spring_boot.controller;

import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message/get/{messageId}", produces = "text/plain")
    public @ResponseBody
    String get(Model model, @PathVariable String messageId, HttpSession session) throws Exception {
        model.addAttribute("message", messageService.get(messageId, (User) session.getAttribute("USER")));
        return "message";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages/{userId}", produces = "text/plain")
    public @ResponseBody
    String getMessagesWithUser(Model model, @PathVariable String userId, HttpSession session, @RequestParam(value = "start", required = false, defaultValue = "0") int start) throws Exception {
        model.addAttribute("messages", messageService.getMessagesBetweenUsers(userId, (User) session.getAttribute("USER"), start));
        return "messages";
    }
}
