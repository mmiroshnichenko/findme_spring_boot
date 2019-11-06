package com.findme_spring_boot.validator;

import com.findme_spring_boot.exception.BadRequestException;

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
