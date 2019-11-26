package com.findme_spring_boot.h2.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.h2.models.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Repository
@Transactional
public class TextDao {
    protected EntityManager entityManager;

    private static final String SELECT_FEED = "INSERT INTO TEXT(ID, TITLE, BODY) VALUES (?1, ?2, ?3)";

    @Autowired
    public TextDao(@Qualifier("h2EntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Text save(Text object) throws InternalServerException {
        try {
            //entityManager.persist(object);
            entityManager.createNativeQuery(SELECT_FEED, Text.class)
                    .setParameter(1, 2)
                    .setParameter(2, object.getTitle())
                    .setParameter(3, object.getBody())
                    .executeUpdate();
            return object;
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
