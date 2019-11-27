package com.findme_spring_boot.service;

import com.findme_spring_boot.dao.oracle.PostDAO;
import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.NotFoundException;
import com.findme_spring_boot.model.oracle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PostService {
    private PostDAO postDAO;
    private UserService userService;
    private RelationshipService relationshipService;

    private static final String URL_REGEX = "^((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";

    @Autowired
    public PostService(PostDAO postDAO, UserService userService, RelationshipService relationshipService) {
        this.postDAO = postDAO;
        this.userService = userService;
        this.relationshipService = relationshipService;
    }

    public Post save(Post post) throws Exception {
        post.setDatePosted(new Date());
        validate(post);

        return postDAO.save(post);
    }

    public Post update(Post post) throws Exception {
        validate(post);

        return postDAO.update(post);
    }

    public void delete(String id) throws Exception {
        postDAO.delete(findById(parsePostId(id)));
    }

    public List<Post> getList(User authUser, PostFilter filter) throws Exception {
        return postDAO.getPostsByFilter(authUser.getId(), filter);
    }

    public List<Post> getFeed(User authUser, int start) throws Exception {
        return postDAO.getFeed(authUser.getId(), start);
    }

    public Post findById(long id) throws Exception{
        Post post = postDAO.findById(id);
        if (post == null) {
            throw new NotFoundException("Error: post(id: " + id + ") was not found");
        }

        return post;
    }

    public Long parsePostId(String id) throws BadRequestException {
        try {
            long paramId = Long.parseLong(id);
            if (paramId <= 0) {
                throw new BadRequestException("Error: incorrect post id: " + id);
            }

            return paramId;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error: incorrect post id format");
        }
    }

    private void validate(Post post) throws Exception {
        Relationship relationship = relationshipService.getRelationshipBetweenUsers(post.getUserPosted().getId(), post.getUserPagePosted().getId());

        if (!post.getUserPosted().equals(post.getUserPagePosted())
            && (relationship == null || relationship.getRelationshipStatus().equals(RelationshipStatus.CONFIRMED))) {
            throw new BadRequestException("Error: you can make post only in own page and friends page");
        }

        if (post.getMessage().length() == 0) {
            throw new BadRequestException("Error: message of post cannot be empty");
        }

        if (post.getMessage().length() > 200) {
            throw new BadRequestException("Error: message of post cannot be more than 200 symbols.");
        }

        if (Pattern.compile(URL_REGEX).matcher(post.getMessage()).find()) {
            throw new BadRequestException("Error: message of post cannot contains URL.");
        }
    }
}
