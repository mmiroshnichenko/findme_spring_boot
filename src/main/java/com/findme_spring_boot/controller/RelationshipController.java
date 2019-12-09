package com.findme_spring_boot.controller;

import com.findme_spring_boot.model.oracle.Relationship;
import com.findme_spring_boot.model.oracle.User;
import com.findme_spring_boot.service.RelationshipService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
