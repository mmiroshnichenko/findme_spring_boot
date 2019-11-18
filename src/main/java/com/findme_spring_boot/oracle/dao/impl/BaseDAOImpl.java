package com.findme_spring_boot.oracle.dao.impl;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.dao.BaseDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Transactional
public class BaseDAOImpl<T> implements BaseDAO<T> {
    private Class<T> typeOfT;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDAOImpl(Class<T> typeOfT) {
        this.typeOfT = typeOfT;
    }

    public T save(T object) throws InternalServerException {
        try {
            entityManager.persist(object);
            return object;
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public T update(T object) throws InternalServerException {
        try {
            entityManager.merge(object);

            return object;
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public void delete(T object) throws InternalServerException {
        try {
            entityManager.remove(object);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
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
