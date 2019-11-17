package com.findme_spring_boot.validator;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.oracle.models.RelationshipStatus;

public class RequestedRelationshipValidator extends BaseRelationshipValidator {
    private final Integer maxRequestCount = 10;

    @Override
    public void check(RelationshipParams params) throws BadRequestException {
        if (params.getNextStatus().equals(RelationshipStatus.REQUESTED)) {
            if (!params.getCurrentStatus().equals(RelationshipStatus.NEW)) {
                throw new BadRequestException("Error: incorrect new REQUESTED status for relationship");
            }

            if (params.getRequestCount() >= maxRequestCount) {
                throw new BadRequestException("Error: max request to friends cannot be great that " + maxRequestCount);
            }
        }

        checkNext(params);
    }
}
