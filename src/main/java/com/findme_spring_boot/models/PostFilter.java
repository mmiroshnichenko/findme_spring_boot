package com.findme_spring_boot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFilter {
    boolean onlyOwner;
    boolean friendsPosts;
    long authorId;
}
