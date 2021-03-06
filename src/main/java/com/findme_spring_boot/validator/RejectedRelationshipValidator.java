package com.findme_spring_boot.validator;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.model.oracle.RelationshipStatus;

public class RejectedRelationshipValidator extends BaseRelationshipValidator {

    @Override
    public void check(RelationshipParams params) throws BadRequestException {
        if (params.getNextStatus().equals(RelationshipStatus.REJECTED)) {
            if (!params.getCurrentStatus().equals(RelationshipStatus.REQUESTED)) {
                throw new BadRequestException("Error: incorrect new REJECTED status for relationship");
            }
        }

        checkNext(params);
    }
}
