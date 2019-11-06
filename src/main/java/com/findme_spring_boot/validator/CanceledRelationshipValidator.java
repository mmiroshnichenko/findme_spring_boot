package com.findme_spring_boot.validator;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.models.RelationshipStatus;

public class CanceledRelationshipValidator extends BaseRelationshipValidator {

    @Override
    public void check(RelationshipParams params) throws BadRequestException {
        if (params.getNextStatus().equals(RelationshipStatus.CANCELED)) {
            if (!params.getCurrentStatus().equals(RelationshipStatus.REQUESTED)) {
                throw new BadRequestException("Error: incorrect new CANCELED status for relationship");
            }
        }

        checkNext(params);
    }
}
