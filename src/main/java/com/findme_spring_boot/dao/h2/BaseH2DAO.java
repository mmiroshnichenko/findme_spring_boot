package com.findme_spring_boot.dao.h2;


public interface BaseH2DAO <T> {
    T save(T object);

    T update(T object);

    void delete(T object);

    T findById(long id);
}
