package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.ForbiddenException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.model.oracle.Relationship;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.RelationshipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RelationshipController {
    private RelationshipService relationshipService;

    private static final Logger relationshipLogger = LogManager.getLogger(RelationshipController.class);

    @Autowired
    public RelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @RequestMapping(path = "/relationship/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addRelationship(HttpSession session, @RequestBody Relationship relationship) throws Exception {
        relationshipService.addRelationship(relationship, (User) session.getAttribute("USER"));
        relationshipLogger.info("New relationship:" + relationship.toString());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/relationship/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateRelationship(HttpSession session, @RequestBody Relationship relationship) throws Exception {
        relationshipService.updateRelationship(relationship);
        relationshipLogger.info("Updated relationship:" + relationship.toString());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/relationship/requests/income", produces = "text/plain")
    public String getIncomeRequests(HttpSession session, Model model) throws Exception {
        List<Relationship> incomeRequests = relationshipService.getIncomeRequests((User) session.getAttribute("USER"));
        model.addAttribute("incomeRequests", incomeRequests);

        return "incomeRequests";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/relationship/requests/outcome", produces = "text/plain")
    public String getOutcomeRequests(HttpSession session, Model model) throws Exception {
        List<Relationship> outcomeRequests = relationshipService.getOutcomeRequests((User) session.getAttribute("USER"));
        model.addAttribute("outcomeRequests", outcomeRequests);

        return "incomeRequests";
    }
}
