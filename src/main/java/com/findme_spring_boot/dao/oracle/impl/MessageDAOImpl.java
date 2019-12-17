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
            "WHERE USER_TO_ID = ?1 AND DATE_READ IS NULL AND DATE_DELETED IS NULL";

    private static final String SELECT_MESSAGES_BETWEEN_USERS = "SELECT * FROM MESSAGE " +
            "WHERE ((USER_FROM_ID = ?1 AND USER_TO_ID = ?2) OR (USER_FROM_ID = ?2 AND USER_TO_ID = ?1)) AND DATE_DELETED IS NULL ORDER BY DATE_SENT DESC";

    private static final String DELETE_CONVERSATION = "UPDATE MESSAGE SET DATE_DELETED = SYSTIMESTAMP " +
            "WHERE ((USER_FROM_ID = ?1 AND USER_TO_ID = ?2) OR (USER_FROM_ID = ?2 AND USER_TO_ID = ?1)) AND DATE_DELETED IS NULL";

    private static final String SET_DATE_READ = "UPDATE MESSAGE SET DATE_READ = SYSTIMESTAMP WHERE ID IN (?1)";

    private static final int limitRows = 20;

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

    public List<Message> getMessagesBetweenUsers(Long userOneId, Long userTwoId, int start) throws InternalServerException {
        try {
            return entityManager.createNativeQuery(SELECT_MESSAGES_BETWEEN_USERS, Message.class)
                    .setParameter(1, userOneId)
                    .setParameter(2, userTwoId)
                    .setFirstResult(start)
                    .setMaxResults(limitRows)
                    .getResultList();
        }  catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public void deleteMessagesWithUser(Long userOneId, Long userTwoId) throws ApiInternalServerException {
        try {
            entityManager.createNativeQuery(DELETE_CONVERSATION)
                    .setParameter(1, userOneId)
                    .setParameter(2, userTwoId)
                    .executeUpdate();
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    public void setDateReadForMessages(List<Long> messageIds) throws InternalServerException {
        try {
            entityManager.createNativeQuery(SET_DATE_READ)
                    .setParameter(1, messageIds)
                    .executeUpdate();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
