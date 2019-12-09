package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;

public interface BaseDAO <T> {
    T save(T object) throws ApiInternalServerException;

    T update(T object) throws ApiInternalServerException;

    void delete(T object) throws ApiInternalServerException;

    T findById(long id) throws InternalServerException;
}
