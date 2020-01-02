package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.model.oracle.Relationship;

import java.util.List;

public interface RelationshipDAO extends BaseDAO<Relationship> {

    Relationship getExistRelationship(Long userFromId, Long userToId);

    List<Relationship> getRelationshipsForUser(Long userId);

    List<Relationship> getIncomeRequests(Long userId);

    List<Relationship> getOutcomeRequests(Long userId);

    int getCountOutcomeRequests(Long userId);

    int getCountFriends(Long userId);

    boolean existConfirmedRelationship(Long userFromId, Long userToId);
}
