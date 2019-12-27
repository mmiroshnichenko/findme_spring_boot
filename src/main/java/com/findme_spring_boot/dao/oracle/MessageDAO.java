package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.model.oracle.Message;
import com.findme_spring_boot.model.oracle.User;

import java.util.List;

public interface MessageDAO extends BaseDAO<Message> {
    int getCountIncomeMessages(Long userId);
    List<Message> getMessagesBetweenUsers(Long userOneId, Long userTwoId, int start);
    void deleteMessagesWithUser(Long userOneId, Long userTwoId);
    void deleteSomeMessages(List<Long> messagesIds);
    void setDateReadForMessages(List<Long> messageIds);
}
