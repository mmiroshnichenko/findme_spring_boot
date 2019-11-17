package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.oracle.models.Relationship;

import java.util.List;

public interface RelationshipDAO extends BaseDAO<Relationship> {

    public Relationship getExistRelationship(Long userFromId, Long userToId);

    public List<Relationship> getRelationshipsForUser(Long userId);

    public List<Relationship> getIncomeRequests(Long userId);

    public List<Relationship> getOutcomeRequests(Long userId);

    public int getCountOutcomeRequests(Long userId);

    public int getCountFriends(Long userId);
}
