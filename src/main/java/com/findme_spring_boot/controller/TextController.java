package com.findme_spring_boot.controller;

import com.findme_spring_boot.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TextController {
    private TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }
}
