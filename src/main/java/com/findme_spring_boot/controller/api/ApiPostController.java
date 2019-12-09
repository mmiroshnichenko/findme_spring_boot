package com.findme_spring_boot.controller.api;

import com.findme_spring_boot.model.oracle.Post;
import com.findme_spring_boot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiPostController {

    private PostService postService;

    @Autowired
    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/save")
    public Post save(@RequestBody Post post) throws Exception {
        return postService.save(post);
    }

    @PutMapping("/post/update")
    public Post update(@RequestBody Post post) throws Exception {
        return postService.update(post);
    }

    @DeleteMapping("/post/delete/{postId}")
    public void delete(@PathVariable String postId) throws Exception {
        postService.delete(postId);
    }
}
