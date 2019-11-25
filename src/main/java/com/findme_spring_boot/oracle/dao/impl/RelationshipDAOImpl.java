package com.findme_spring_boot.oracle.dao.impl;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.dao.RelationshipDAO;
import com.findme_spring_boot.oracle.models.Relationship;
import com.findme_spring_boot.oracle.models.RelationshipStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RelationshipDAOImpl extends BaseDAOImpl<Relationship> implements RelationshipDAO {
    private static final String SELECT_EXIST_RELATIONSHIP = "SELECT * FROM RELATIONSHIP " +
            "WHERE (USER_FROM_ID = ?1 AND USER_TO_ID = ?2) OR (USER_FROM_ID = ?2 AND USER_TO_ID = ?1)";

    private static final String SELECT_RELATIONSHIPS_FOR_USER = "SELECT * FROM RELATIONSHIP " +
            "WHERE USER_FROM_ID = ?1 OR USER_TO_ID = ?1";

    private static final String SELECT_INCOME_REQ = "SELECT * FROM RELATIONSHIP " +
            "WHERE USER_TO_ID = ?1 AND STATUS = ?2";

    private static final String SELECT_OUTCOME_REQ = "SELECT * FROM RELATIONSHIP " +
            "WHERE USER_FROM_ID = ?1 AND STATUS = ?2";

    private static final String SELECT_COUNT_OUTCOME_REQUESTS = "SELECT COUNT(*) FROM RELATIONSHIP " +
            "WHERE USER_FROM_ID = ?1 AND STATUS = ?2";

    private static final String SELECT_COUNT_FRIENDS = "SELECT COUNT(*) FROM RELATIONSHIP " +
            "WHERE (USER_FROM_ID = ?1 OR USER_TO_ID = ?1) AND STATUS = ?2";

    public RelationshipDAOImpl() {
        super(Relationship.class);
    }

    public Relationship getExistRelationship(Long userFromId, Long userToId) throws InternalServerException{
        try {
            Query query = entityManager.createNativeQuery(SELECT_EXIST_RELATIONSHIP, Relationship.class);
            query.setParameter(1, userFromId);
            query.setParameter(2, userToId);

            return (Relationship) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }  catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public List<Relationship> getRelationshipsForUser(Long userId) throws InternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_RELATIONSHIPS_FOR_USER, Relationship.class);
            query.setParameter(1, userId);

            return query.getResultList();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public List<Relationship> getIncomeRequests(Long userId) throws InternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_INCOME_REQ, Relationship.class);
            query.setParameter(1, userId);
            query.setParameter(2, RelationshipStatus.REQUESTED.toString());

            return query.getResultList();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    public List<Relationship> getOutcomeRequests(Long userId) throws InternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_OUTCOME_REQ, Relationship.class);
            query.setParameter(1, userId);
            query.setParameter(2, RelationshipStatus.REQUESTED.toString());

            return query.getResultList();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    public int getCountOutcomeRequests(Long userId) throws InternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_COUNT_OUTCOME_REQUESTS);
            query.setParameter(1, userId);
            query.setParameter(2, RelationshipStatus.REQUESTED.toString());

            return ((Number) query.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    public int getCountFriends(Long userId) throws InternalServerException {
        try {
            Query query = entityManager.createNativeQuery(SELECT_COUNT_FRIENDS);
            query.setParameter(1, userId);
            query.setParameter(2, RelationshipStatus.CONFIRMED.toString());

            return ((Number) query.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
