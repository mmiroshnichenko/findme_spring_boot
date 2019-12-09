package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;
import com.findme_spring_boot.model.oracle.Message;

import java.util.List;

public interface MessageDAO extends BaseDAO<Message> {
    int getCountIncomeMessages(Long userId) throws ApiInternalServerException;
    List<Message> getMessagesBetweenUsers(Long userFromId, Long userToId) throws InternalServerException;
}
