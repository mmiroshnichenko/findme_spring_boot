package com.findme_spring_boot.oracle.dao.impl;

import com.findme_spring_boot.oracle.dao.MessageDAO;
import com.findme_spring_boot.oracle.models.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageDAOImpl extends BaseDAOImpl<Message> implements MessageDAO {
    public MessageDAOImpl() {
        super(Message.class);
    }
}
