package com.findme_spring_boot.controller;

import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserService userService;

    private static final Logger userLogger = LogManager.getLogger(UserController.class);
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody User user) throws Exception {
        userService.update(user);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable String userId) throws Exception {
        userService.delete(userId);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}", produces = "text/plain")
    public String get(Model model, @PathVariable String userId) throws Exception {
        model.addAttribute("user", userService.findById(userService.parseUserId(userId)));
        return "profile";
    }

    @RequestMapping(path = "/register-user", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@ModelAttribute User user) throws Exception {
        userService.save(user);
        userLogger.info("User with id: " + user.getId() + " was registered");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String home() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(HttpSession session, HttpServletRequest request) throws Exception {
        User user = userService.login(request.getParameter("email"), request.getParameter("password"));
        session.setAttribute("USER", user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/user/" + user.getId());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpSession session) throws Exception {
        session.invalidate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/login");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
