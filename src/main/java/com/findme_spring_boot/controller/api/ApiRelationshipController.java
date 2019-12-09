package com.findme_spring_boot.controller.api;

import com.findme_spring_boot.controller.RelationshipController;
import com.findme_spring_boot.model.oracle.Relationship;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.RelationshipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class ApiRelationshipController {
    private RelationshipService relationshipService;

    private static final Logger relationshipLogger = LogManager.getLogger(RelationshipController.class);

    @Autowired
    public ApiRelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @PostMapping("/relationship/add")
    public Relationship addRelationship(HttpSession session, @RequestBody Relationship relationship) throws Exception {
        relationshipService.addRelationship(relationship, (User) session.getAttribute("USER"));
        relationshipLogger.info("New relationship:" + relationship.toString());

        return relationship;
    }

    @PutMapping("/relationship/update")
    public Relationship updateRelationship(@RequestBody Relationship relationship) throws Exception {
        relationshipService.updateRelationship(relationship);
        relationshipLogger.info("Updated relationship:" + relationship.toString());

        return relationship;
    }
}
