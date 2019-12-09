package com.findme_spring_boot.controller.api;

import com.findme_spring_boot.model.h2.Text;
import com.findme_spring_boot.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTextController {
    private TextService textService;

    @Autowired
    public ApiTextController(TextService textService) {
        this.textService = textService;
    }

    @PostMapping("/text/save")
    public Text save(@RequestBody Text text) throws Exception {
        return textService.save(text);
    }
}
