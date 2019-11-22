package com.findme_spring_boot.oracle.dao;

import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.oracle.models.Post;
import com.findme_spring_boot.oracle.models.PostFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO you shouldn't use these annotations for interface
@Repository
@Transactional
public interface PostDAO extends BaseDAO<Post> {

    public List<Post> getPostsByFilter(Long authUserId, PostFilter filter) throws InternalServerException;

    public List<Post> getFeed(Long userId, int start) throws InternalServerException;
}
