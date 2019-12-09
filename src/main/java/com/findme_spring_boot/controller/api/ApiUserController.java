package com.findme_spring_boot.controller.api;

import com.findme_spring_boot.controller.UserController;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class ApiUserController {
    private UserService userService;

    private static final Logger userLogger = LogManager.getLogger(UserController.class);

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user/update")
    public User update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @DeleteMapping("/user/delete/{userId}")
    public void delete(@PathVariable String userId) throws Exception {
        userService.delete(userId);
    }

    @PostMapping("/register-user")
    public User registerUser(@ModelAttribute User user) throws Exception {
        userService.save(user);
        userLogger.info("User with id: " + user.getId() + " was registered");

        return user;
    }

    @PostMapping("/login")
    public void loginUser(HttpSession session, HttpServletRequest request) throws Exception {
        User user = userService.login(request.getParameter("email"), request.getParameter("password"));
        session.setAttribute("USER", user);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) throws Exception {
        session.invalidate();
    }
}
