package com.findme_spring_boot.model.oracle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFilter {
    boolean onlyOwner;
    boolean friendsPosts;
    long authorId;
}
