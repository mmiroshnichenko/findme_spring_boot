package com.findme_spring_boot.service;

import com.findme_spring_boot.dao.oracle.RelationshipDAO;
import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.model.oracle.Relationship;
import com.findme_spring_boot.model.oracle.RelationshipStatus;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RelationshipService {
    private final Integer maxRequestCount = 10;

    private RelationshipDAO relationshipDAO;

    @Autowired
    public RelationshipService(RelationshipDAO relationshipDAO) {
        this.relationshipDAO = relationshipDAO;
    }

    public void addRelationship(Relationship relationship, User authUser) throws Exception {
        validateNewRelationship(relationship, authUser);
        relationship.setDateModify(new Date());

        relationshipDAO.save(relationship);
    }

    public void updateRelationship(Relationship relationship) throws Exception {
        validateUpdatedRelationship(relationship);
        relationship.setDateModify(new Date());

        relationshipDAO.update(relationship);
    }

    public List<Relationship> getRelationshipsForUser(Long userId) throws Exception {
        return relationshipDAO.getRelationshipsForUser(userId);
    }

    public List<Relationship> getIncomeRequests(User user) throws Exception {
        return relationshipDAO.getIncomeRequests(user.getId());
    }

    public List<Relationship> getOutcomeRequests(User user) throws Exception {
        return relationshipDAO.getOutcomeRequests(user.getId());
    }

    public Relationship getRelationshipBetweenUsers(long user1Id, long user2Id) throws Exception {
        return relationshipDAO.getExistRelationship(user1Id, user2Id);
    }

    public boolean existConfirmedRelationship(long user1Id, long user2Id) throws Exception {
        return relationshipDAO.existConfirmedRelationship(user1Id, user2Id);
    }

    private void validateUpdatedRelationship(Relationship relationship) throws Exception {
        Relationship dbRelationship = relationshipDAO.findById(relationship.getId());
        if (!relationship.getUserFrom().getId().equals(dbRelationship.getUserFrom().getId())) {
            throw new BadRequestException("Error: incorrect userFrom");
        }
        if (!relationship.getUserTo().getId().equals(dbRelationship.getUserTo().getId())) {
            throw new BadRequestException("Error: incorrect userTo");
        }

        BaseRelationshipValidator relationshipValidator = new ConfirmedRelationshipValidator();

        relationshipValidator.linkWith(new CanceledRelationshipValidator())
                .linkWith(new RejectedRelationshipValidator())
                .linkWith(new DeletedRelationshipValidator())
                .linkWith(new RequestedRelationshipValidator());

        relationshipValidator.check(RelationshipParams.builder()
                .currentStatus(dbRelationship.getRelationshipStatus())
                .nextStatus(relationship.getRelationshipStatus())
                .dateModify(dbRelationship.getDateModify())
                .requestCount(relationshipDAO.getCountOutcomeRequests(relationship.getUserFrom().getId()))
                .friendsCount(relationshipDAO.getCountFriends(relationship.getUserFrom().getId()))
                .build());
    }

    private void validateNewRelationship(Relationship relationship, User authUser) throws Exception {
        if (!authUser.getId().equals(relationship.getUserFrom().getId())) {
            throw new BadRequestException("Error: incorrect userFrom");
        }
        if (relationshipDAO.getExistRelationship(authUser.getId(), relationship.getUserTo().getId()) != null) {
            throw new BadRequestException("Error: active relationship already exists");
        }

        if (!relationship.getRelationshipStatus().equals(RelationshipStatus.NEW)) {
            throw new BadRequestException("Error: incorrect new REQUESTED status for relationship");
        }

        if (relationshipDAO.getCountOutcomeRequests(relationship.getUserFrom().getId()) >= maxRequestCount) {
            throw new BadRequestException("Error: max request to friends cannot be great that " + maxRequestCount);
        }
    }
}
