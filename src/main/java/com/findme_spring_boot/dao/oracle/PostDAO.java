package com.findme_spring_boot.dao.oracle;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.model.oracle.Post;
import com.findme_spring_boot.model.oracle.PostFilter;

import java.util.List;

public interface PostDAO extends BaseDAO<Post> {

    List<Post> getPostsByFilter(Long authUserId, PostFilter filter) throws InternalServerException;

    List<Post> getFeed(Long userId, int start) throws InternalServerException;
}
