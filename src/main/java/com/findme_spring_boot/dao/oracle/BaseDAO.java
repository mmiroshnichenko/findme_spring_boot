package com.findme_spring_boot.dao.oracle;

public interface BaseDAO <T> {
    T save(T object);

    T update(T object);

    void delete(T object);

    T findById(long id);
}
