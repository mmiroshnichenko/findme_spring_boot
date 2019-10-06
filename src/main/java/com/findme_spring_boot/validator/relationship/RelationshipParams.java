package com.findme_spring_boot.validator.relationship;

import com.findme_spring_boot.models.RelationshipStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RelationshipParams {
    private RelationshipStatus currentStatus;
    private RelationshipStatus nextStatus;
    private Integer friendsCount;
    private Integer requestCount;
    private Date dateModify;
}
