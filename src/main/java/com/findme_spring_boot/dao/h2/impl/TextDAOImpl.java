package com.findme_spring_boot.dao.h2.impl;

import com.findme_spring_boot.dao.h2.TextDAO;
import com.findme_spring_boot.model.h2.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TextDAOImpl extends BaseH2DAOImpl<Text> implements TextDAO {
    @Autowired
    public TextDAOImpl(@Qualifier("h2EntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
        this.setClass(Text.class);
    }
}
