package com.findme_spring_boot.dao.oracle.impl;

import com.findme_spring_boot.dao.oracle.BaseDAO;
import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class BaseDAOImpl<T> implements BaseDAO<T> {
    private Class<T> typeOfT;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDAOImpl(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    public T save(T object) throws ApiInternalServerException {
        try {
            entityManager.persist(object);
            return object;
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    public T update(T object) throws ApiInternalServerException {
        try {
            entityManager.merge(object);

            return object;
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    public void delete(T object) throws ApiInternalServerException {
        try {
            entityManager.remove(object);
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    public T findById(long id) throws InternalServerException {
        try {
            return entityManager.find(this.typeOfT, id);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
