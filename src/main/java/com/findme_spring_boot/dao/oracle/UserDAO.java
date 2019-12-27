package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.model.oracle.User;

public interface UserDAO extends BaseDAO<User> {

    int countUsersWithEmailOrPhone(String email, String phone);

    User getUserByEmailAndPassword(String email, String password);
}
