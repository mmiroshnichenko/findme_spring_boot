package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.oracle.models.Post;
import com.findme_spring_boot.oracle.models.PostFilter;
import com.findme_spring_boot.oracle.models.User;
import com.findme_spring_boot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/post/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> save(@RequestBody Post post) {
        try {
            postService.save(post);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/list", produces = "text/plain")
    public String getList(Model model, HttpSession session, PostFilter filter) {
        try {
            List<Post> postList = postService.getList((User) session.getAttribute("USER"), filter);
            model.addAttribute("postList", postList);
            return "postList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/feed", produces = "text/plain")
    public String getFeed(Model model, HttpSession session, @RequestParam(value = "start", required = false, defaultValue = "0") int start) {
        try {
            List<Post> feed = postService.getFeed((User) session.getAttribute("USER"), start);
            model.addAttribute("feed", feed);
            return "feed";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/post/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody Post post) {
        try {
            postService.update(post);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/post/delete/{postId}")
    public ResponseEntity<String> delete(@PathVariable String postId) {
        try {
            postService.delete(postId);
            return new ResponseEntity<>("post deleted", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/{postId}", produces = "text/plain")
    public String get(Model model, @PathVariable String postId) {
        try {
            model.addAttribute("post", postService.findById(postService.parsePostId(postId)));
            return "post";
        } catch (NotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/notFound";
        } catch (BadRequestException | NumberFormatException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/badRequest";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errors/internalError";
        }
    }
}
