package com.findme_spring_boot.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class BaseDAO <T> {
    private Class<T> typeOfT;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDAO(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    //TODO where is error handling?? it is super important, I wrote about that before
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
