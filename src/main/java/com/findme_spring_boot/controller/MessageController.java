package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.oracle.models.Message;
import com.findme_spring_boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(path = "/message/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody Message message) {
        try {
            messageService.save(message);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/message/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody Message message) {
        try {
            messageService.update(message);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/message/delete/{messageId}")
    public ResponseEntity<String> delete(@PathVariable String messageId) {
        try {
            messageService.delete(messageId);
            return new ResponseEntity<>("message deleted", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/message/get/{messageId}", produces = "text/plain")
    public @ResponseBody
    String get(Model model, @PathVariable String messageId) {
        try {
            model.addAttribute("message", messageService.findById(messageService.parseMessageId(messageId)));
            return "message";
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/notFound";
        } catch (BadRequestException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/badRequest";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }
}
