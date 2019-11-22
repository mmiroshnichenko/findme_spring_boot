package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;

public interface BaseDAO <T> {
    //TODO all methods in interface are public
    public T save(T object) throws InternalServerException;

    public T update(T object) throws InternalServerException;

    public void delete(T object) throws InternalServerException;

    public T findById(long id) throws InternalServerException;
}
