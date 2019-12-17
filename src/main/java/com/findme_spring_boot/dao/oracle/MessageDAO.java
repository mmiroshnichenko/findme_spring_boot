package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;
import com.findme_spring_boot.model.oracle.Message;
import com.findme_spring_boot.model.oracle.User;

import java.util.List;

public interface MessageDAO extends BaseDAO<Message> {
    int getCountIncomeMessages(Long userId) throws ApiInternalServerException;
    List<Message> getMessagesBetweenUsers(Long userOneId, Long userTwoId, int start) throws InternalServerException;
    void deleteMessagesWithUser(Long userOneId, Long userTwoId) throws ApiInternalServerException;
    void setDateReadForMessages(List<Long> messageIds) throws InternalServerException;
}
