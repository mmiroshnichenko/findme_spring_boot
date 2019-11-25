package com.findme_spring_boot.service;

import com.findme_spring_boot.oracle.dao.MessageDAO;
import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.oracle.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    private MessageDAO messageDAO;
    private UserService userService;

    @Autowired
    public MessageService(MessageDAO messageDAO, UserService userService) {
        this.messageDAO = messageDAO;
        this.userService = userService;
    }

    public Message save(Message message) throws Exception {
        validate(message);
        message.setDateSent(new Date());
        message.setUserFrom(userService.findById(message.getUserFrom().getId()));
        message.setUserTo(userService.findById(message.getUserTo().getId()));

        return messageDAO.save(message);
    }

    public Message update(Message message) throws Exception {
        validate(message);
        message.setUserFrom(userService.findById(message.getUserFrom().getId()));
        message.setUserTo(userService.findById(message.getUserTo().getId()));

        return messageDAO.update(message);
    }

    public void delete(String id) throws Exception {
        messageDAO.delete(findById(parseMessageId(id)));
    }

    public Message findById(long id) throws Exception{
        Message message = messageDAO.findById(id);
        if (message == null) {
            throw new NotFoundException("Error: message(id: " + id + ") was not found");
        }

        return message;
    }

    public Long parseMessageId(String id) throws BadRequestException {
        try {
            long paramId = Long.parseLong(id);
            if (paramId <= 0) {
                throw new BadRequestException("Error: incorrect message id: " + id);
            }

            return paramId;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error: incorrect message id format");
        }
    }

    private void validate(Message message) throws Exception {
        if (message.getId() != null) {
            findById(message.getId());
        }
        if (message.getText() == null || message.getText().isEmpty()) {
            throw new BadRequestException("Error: text is required");
        }
        if (message.getUserFrom() == null) {
            throw new BadRequestException("Error: user from is required");
        }
        if (message.getUserTo() == null) {
            throw new BadRequestException("Error: user to is required");
        }
    }
}
