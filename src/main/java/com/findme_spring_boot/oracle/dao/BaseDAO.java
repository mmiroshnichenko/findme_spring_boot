package com.findme_spring_boot.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseDAO <T> {
    public T save(T object);

    public T update(T object);

    public void delete(T object);

    public T findById(long id);
}
