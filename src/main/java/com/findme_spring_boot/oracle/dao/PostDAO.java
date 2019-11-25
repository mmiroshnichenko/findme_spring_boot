package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.models.Post;
import com.findme_spring_boot.oracle.models.PostFilter;

import java.util.List;

public interface PostDAO extends BaseDAO<Post> {

    public List<Post> getPostsByFilter(Long authUserId, PostFilter filter) throws InternalServerException;

    public List<Post> getFeed(Long userId, int start) throws InternalServerException;
}
