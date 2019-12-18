package com.findme_spring_boot.service;

import com.findme_spring_boot.dao.oracle.MessageDAO;
import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.model.oracle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    private MessageDAO messageDAO;
    private RelationshipService relationshipService;
    private UserService userService;

    @Autowired
    public MessageService(MessageDAO messageDAO, RelationshipService relationshipService, UserService userService) {
        this.messageDAO = messageDAO;
        this.relationshipService = relationshipService;
        this.userService = userService;
    }

    public Message save(Message message, User authUser) throws Exception {
        validate(message, authUser);
        message.setDateSent(new Date());

        return messageDAO.save(message);
    }

    public Message update(Message message, User authUser) throws Exception {
        validate(message, authUser);

        return messageDAO.update(message);
    }

    public void delete(String id, User authUser) throws Exception {
        Message message = findById(parseMessageId(id));
        validateDeleting(message, authUser);

        message.setDateDeleted(new Date());
        messageDAO.update(message);
    }

    public void deleteMessagesWithUser(String userId, User authUser) throws Exception {
        messageDAO.deleteMessagesWithUser(userService.parseUserId(userId), authUser.getId());
    }

    public void deleteSomeMessages(String[] messagesIds, User authUser) throws Exception {
        for (String messageId: messagesIds) {
            delete(messageId, authUser);
        }
    }

    public Message get(String id, User authUser) throws Exception {
        Message message = findById(parseMessageId(id));
        validateReading(message, authUser);
        if (message.getUserTo().getId().equals(authUser.getId()) && message.getDateRead() == null) {
            message.setDateRead(new Date());
            messageDAO.update(message);
        }

        return message;
    }

    public int getCountIncomeMessages(User authUser) throws Exception {
        return messageDAO.getCountIncomeMessages(authUser.getId());
    }

    public List<Message> getMessagesBetweenUsers(String userId, User authUser, int start) throws Exception {
        List<Message> messages = messageDAO.getMessagesBetweenUsers(userService.parseUserId(userId), authUser.getId(), start);
        List<Long> messagesIds = new ArrayList<>();
        for (Message message: messages) {
            if (message.getUserTo().getId().equals(authUser.getId()) && message.getDateRead() == null) {
                messagesIds.add(message.getId());
            }
        }

        if (!messagesIds.isEmpty()) {
            messageDAO.setDateReadForMessages(messagesIds);
        }

        return messages;
    }

    private Long parseMessageId(String id) throws BadRequestException {
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

    private Message findById(long id) throws Exception {
        Message message = messageDAO.findById(id);
        if (message == null) {
            throw new NotFoundException("Error: message(id: " + id + ") was not found");
        }

        return message;
    }

    private void checkAuthor(Message message, User authUser) throws Exception {
        if (!message.getUserFrom().getId().equals(authUser.getId())) {
            throw new BadRequestException("Error: you cannot update this message");
        }
    }

    private void validateReading(Message message, User authUser) throws Exception {
        if (!message.getUserFrom().getId().equals(authUser.getId()) && !message.getUserTo().getId().equals(authUser.getId())) {
            throw new BadRequestException("Error: you cannot read this message");
        }
    }

    private void validateDeleting(Message message, User authUser) throws Exception {
        checkAuthor(message, authUser);

        if (message.getDateRead() != null) {
            throw new BadRequestException("Error: you cannot delete this message.");
        }
    }

    private void validate(Message message, User authUser) throws Exception {
        checkAuthor(message, authUser);

        Relationship relationship = relationshipService.getRelationshipBetweenUsers(message.getUserFrom().getId(), message.getUserTo().getId());
        if (relationship == null || !relationship.getRelationshipStatus().equals(RelationshipStatus.CONFIRMED)) {
            throw new BadRequestException("Error: you can send message only to friends");
        }

        if (message.getText() == null || message.getText().isEmpty()) {
            throw new BadRequestException("Error: text of message cannot be empty");
        }

        if (message.getText().length() > 140) {
            throw new BadRequestException("Error: text of message cannot be more than 140 symbols.");
        }
    }
}
