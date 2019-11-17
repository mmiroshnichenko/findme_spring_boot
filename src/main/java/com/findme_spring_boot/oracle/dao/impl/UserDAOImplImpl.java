package com.findme_spring_boot.oracle.dao.impl;

import com.findme_spring_boot.oracle.dao.UserDAO;
import com.findme_spring_boot.oracle.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
@Transactional
public class UserDAOImplImpl extends BaseDAOImpl<User> implements UserDAO {
    private static final String findUserDuplicate = "SELECT COUNT(*) FROM USER_FM WHERE EMAIL = ?1 OR PHONE = ?2";
    private static final String SELECT_BY_EMAILS_PASSWORD = "SELECT * FROM USER_FM WHERE EMAIL = ?1 AND PASSWORD = ?2";
    //TODO I've updated naming for queries, it is commonn format in java

    public UserDAOImplImpl() {
        super(User.class);
    }

    public int countUsersWithEmailOrPhone(String email, String phone) {
        Query query = entityManager.createNativeQuery(findUserDuplicate);
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
