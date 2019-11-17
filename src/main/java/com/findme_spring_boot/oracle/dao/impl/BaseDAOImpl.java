package com.findme_spring_boot.oracle.dao.impl;

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
