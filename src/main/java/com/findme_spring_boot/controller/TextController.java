package com.findme_spring_boot.controller;

import com.findme_spring_boot.h2.models.Text;
import com.findme_spring_boot.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TextController {
    private TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @RequestMapping(path = "/text/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody Text text) {
        try {
            textService.save(text);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
