package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.models.Post;
import com.findme_spring_boot.oracle.models.PostFilter;

import java.util.List;

public interface PostDAO extends BaseDAO<Post> {

    List<Post> getPostsByFilter(Long authUserId, PostFilter filter) throws InternalServerException;

    List<Post> getFeed(Long userId, int start) throws InternalServerException;
}
