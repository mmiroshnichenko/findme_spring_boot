package com.findme_spring_boot.service;

import com.findme_spring_boot.oracle.dao.UserDAO;
import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.ForbiddenException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.oracle.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User save(User user) throws Exception {
        validateRegistration(user);
        user.setDateRegistered(new Date());

        return userDAO.save(user);
    }

    public User update(User user) throws Exception {
        validateUser(user);
        return userDAO.update(user);
    }

    public void delete(long id) throws Exception {
        userDAO.delete(findById(id));
    }

    public User findById(long id) throws Exception {
        User user = userDAO.findById(id);
        if (user == null) {
            throw new NotFoundException("Error: user(id: " + id + ") was not found");
        }

        return user;
    }

    public User login(String email, String password) throws Exception {
        if (email.isEmpty() || password.isEmpty()) {
            throw new BadRequestException("Error: email and password is required");
        }

        User user = userDAO.getUserByEmailAndPassword(email, password);
        if (user == null) {
            throw new ForbiddenException("Error: email or password is incorrect");
        }

        return user;
    }

    private void validateUser(User user) throws Exception {
        if (user.getId() != null) {
            findById(user.getId());
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new BadRequestException("Error: first name is required");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new BadRequestException("Error: last name is required");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new BadRequestException("Error: phone is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BadRequestException("Error: email is required");
        }
    }

    private void validateRegistration(User user) throws Exception {
        validateUser(user);

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Error: password is required");
        }
        if (userDAO.countUsersWithEmailOrPhone(user.getEmail(), user.getPhone()) > 0) {
            throw new BadRequestException("Error: users with entered email or phone already exist");
        }
    }
}
