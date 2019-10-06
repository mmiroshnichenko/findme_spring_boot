package com.findme_spring_boot.dao;

import com.findme_spring_boot.models.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MessageDAO extends BaseDAO<Message> {
    public MessageDAO() {
        super(Message.class);
    }
}
