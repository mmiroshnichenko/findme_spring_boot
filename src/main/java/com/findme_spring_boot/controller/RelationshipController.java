package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.ForbiddenException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.oracle.models.Relationship;
import com.findme_spring_boot.oracle.models.User;
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
    public ResponseEntity<String> addRelationship(HttpSession session, @RequestBody Relationship relationship) {
        try {
            relationshipService.addRelationship(relationship, (User) session.getAttribute("USER"));
            relationshipLogger.info("New relationship:" + relationship.toString());
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ForbiddenException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/relationship/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> updateRelationship(HttpSession session, @RequestBody Relationship relationship) {
        try {
            relationshipService.updateRelationship(relationship);
            relationshipLogger.info("Updated relationship:" + relationship.toString());
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ForbiddenException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/relationship/requests/income", produces = "text/plain")
    public String getIncomeRequests(HttpSession session, Model model) {
        try {
            List<Relationship> incomeRequests = relationshipService.getIncomeRequests((User) session.getAttribute("USER"));
            model.addAttribute("incomeRequests", incomeRequests);

            return "incomeRequests";
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/notFound";
        } catch (BadRequestException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/badRequest";
        } catch (ForbiddenException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/forbidden";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/relationship/requests/outcome", produces = "text/plain")
    public String getOutcomeRequests(HttpSession session, Model model) {
        try {
            List<Relationship> outcomeRequests = relationshipService.getOutcomeRequests((User) session.getAttribute("USER"));
            model.addAttribute("outcomeRequests", outcomeRequests);

            return "incomeRequests";
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/notFound";
        } catch (BadRequestException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/badRequest";
        } catch (ForbiddenException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/forbidden";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }
}
