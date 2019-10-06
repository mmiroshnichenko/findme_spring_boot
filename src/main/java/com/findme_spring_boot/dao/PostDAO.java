package com.findme_spring_boot.dao;

import com.findme_spring_boot.models.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PostDAO extends BaseDAO<Post> {
    public PostDAO() {
        super(Post.class);
    }
}
