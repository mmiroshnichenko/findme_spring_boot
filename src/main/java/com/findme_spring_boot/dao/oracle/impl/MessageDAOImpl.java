package com.findme_spring_boot.dao.oracle.impl;

import com.findme_spring_boot.dao.oracle.MessageDAO;
import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;
import com.findme_spring_boot.model.oracle.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageDAOImpl extends BaseDAOImpl<Message> implements MessageDAO {
    private static final String SELECT_COUNT_INCOME_MESSAGES = "SELECT COUNT(*) FROM MESSAGE " +
            "WHERE USER_TO_ID = ?1 AND DATE_READ IS NULL";

    private static final String SELECT_MESSAGES_BETWEEN_USERS = "SELECT * FROM MESSAGE " +
            "WHERE USER_FROM_ID = ?1 AND USER_TO_ID = ?2";

    public MessageDAOImpl() {
        super(Message.class);
    }

    public int getCountIncomeMessages(Long userId) throws ApiInternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_COUNT_INCOME_MESSAGES);
            query.setParameter(1, userId);

            return ((Number) query.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    public List<Message> getMessagesBetweenUsers(Long userFromId, Long userToId) throws InternalServerException {
        try {
            return entityManager.createNativeQuery(SELECT_MESSAGES_BETWEEN_USERS, Message.class)
                    .setParameter(1, userFromId)
                    .setParameter(2, userToId)
                    .getResultList();
        }  catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
