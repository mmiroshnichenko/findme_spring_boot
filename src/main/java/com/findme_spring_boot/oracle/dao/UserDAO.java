package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserDAO extends BaseDAO<User> {

    public int countUsersWithEmailOrPhone(String email, String phone) throws InternalServerException;

    public User getUserByEmailAndPassword(String email, String password) throws InternalServerException;
}
