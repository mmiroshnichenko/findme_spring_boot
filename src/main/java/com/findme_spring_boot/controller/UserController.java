package com.findme_spring_boot.controller;

import com.findme_spring_boot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    private static final Logger userLogger = LogManager.getLogger(UserController.class);
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}", produces = "text/plain")
    public String get(Model model, @PathVariable String userId) throws Exception {
        model.addAttribute("user", userService.findById(userService.parseUserId(userId)));
        return "profile";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String home() {
        return "login";
    }
}
