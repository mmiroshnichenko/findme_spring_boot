package com.findme_spring_boot.dao.oracle.impl;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.dao.oracle.UserDAO;
import com.findme_spring_boot.model.oracle.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {
    private static final String SELECT_USER_DUBLICATE = "SELECT COUNT(*) FROM USER_FM WHERE EMAIL = ?1 OR PHONE = ?2";
    private static final String SELECT_BY_EMAILS_PASSWORD = "SELECT * FROM USER_FM WHERE EMAIL = ?1 AND PASSWORD = ?2";

    public UserDAOImpl() {
       super(User.class);
    }

    public int countUsersWithEmailOrPhone(String email, String phone) {
        Query query = entityManager.createNativeQuery(SELECT_USER_DUBLICATE);
        query.setParameter(1, email);
        query.setParameter(2, phone);

        return ((Number) query.getSingleResult()).intValue();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            Query query = entityManager.createNativeQuery(SELECT_BY_EMAILS_PASSWORD, User.class);
            query.setParameter(1, email);
            query.setParameter(2, password);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
