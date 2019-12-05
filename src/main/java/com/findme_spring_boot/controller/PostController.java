package com.findme_spring_boot.controller;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.model.oracle.Post;
import com.findme_spring_boot.model.oracle.PostFilter;
import com.findme_spring_boot.model.oracle.User;
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
    public ResponseEntity<String> save(@RequestBody Post post) throws Exception {
        postService.save(post);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/list", produces = "text/plain")
    public String getList(Model model, HttpSession session, PostFilter filter) throws Exception {
        List<Post> postList = postService.getList((User) session.getAttribute("USER"), filter);
        model.addAttribute("postList", postList);
        return "postList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/feed", produces = "text/plain")
    public String getFeed(Model model, HttpSession session, @RequestParam(value = "start", required = false, defaultValue = "0") int start) throws Exception {
            List<Post> feed = postService.getFeed((User) session.getAttribute("USER"), start);
            model.addAttribute("feed", feed);
            return "feed";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/post/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody Post post) throws Exception {
        postService.update(post);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/post/delete/{postId}")
    public ResponseEntity<String> delete(@PathVariable String postId) throws Exception {
        postService.delete(postId);
        return new ResponseEntity<>("post deleted", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/{postId}", produces = "text/plain")
    public String get(Model model, @PathVariable String postId) throws Exception {
        model.addAttribute("post", postService.findById(postService.parsePostId(postId)));
        return "post";
    }
}
