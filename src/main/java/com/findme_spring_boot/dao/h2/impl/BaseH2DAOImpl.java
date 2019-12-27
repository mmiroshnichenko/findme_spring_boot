package com.findme_spring_boot.dao.h2.impl;

import com.findme_spring_boot.dao.h2.BaseH2DAO;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional("h2TransactionManager")
public class BaseH2DAOImpl<T> implements BaseH2DAO<T> {
    private Class<T> typeOfT;

    protected EntityManager entityManager;

    protected void setClass(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    public T save(T object) {
        entityManager.persist(object);
        return object;
    }

    public T update(T object) {
        entityManager.merge(object);

        return object;
    }

    public void delete(T object) {
        entityManager.remove(object);
    }

    public T findById(long id) {
        return entityManager.find(this.typeOfT, id);
    }
}
