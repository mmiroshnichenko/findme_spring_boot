package com.findme_spring_boot.dao.h2;

import com.findme_spring_boot.exception.InternalServerException;

public interface BaseH2DAO <T> {
    T save(T object) throws InternalServerException;

    T update(T object) throws InternalServerException;

    void delete(T object) throws InternalServerException;

    T findById(long id) throws InternalServerException;
}
