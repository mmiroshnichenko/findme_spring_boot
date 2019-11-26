package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.models.Relationship;

import java.util.List;

public interface RelationshipDAO extends BaseDAO<Relationship> {

    Relationship getExistRelationship(Long userFromId, Long userToId) throws InternalServerException;

    List<Relationship> getRelationshipsForUser(Long userId) throws InternalServerException;

    List<Relationship> getIncomeRequests(Long userId) throws InternalServerException;

    List<Relationship> getOutcomeRequests(Long userId) throws InternalServerException;

    int getCountOutcomeRequests(Long userId) throws InternalServerException;

    int getCountFriends(Long userId) throws InternalServerException;
}
