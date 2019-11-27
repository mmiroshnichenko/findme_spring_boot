package com.findme_spring_boot.dao.oracle.impl;

import com.findme_spring_boot.dao.oracle.MessageDAO;
import com.findme_spring_boot.model.oracle.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl extends BaseDAOImpl<Message> implements MessageDAO {
    public MessageDAOImpl() {
        super(Message.class);
    }
}
