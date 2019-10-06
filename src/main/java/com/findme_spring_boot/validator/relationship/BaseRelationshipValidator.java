package com.findme_spring_boot.validator.relationship;

import com.findme_spring_boot.exception.BadRequestException;

import java.util.List;

public abstract class BaseRelationshipValidator {
    private BaseRelationshipValidator next;

    public BaseRelationshipValidator linkWith(BaseRelationshipValidator next) {
        this.next = next;

        return next;
    }

    public abstract void check(RelationshipParams params) throws BadRequestException;

    protected void checkNext(RelationshipParams params) throws BadRequestException {
        if (next != null) {
            next.check(params);
        }
    }
}
