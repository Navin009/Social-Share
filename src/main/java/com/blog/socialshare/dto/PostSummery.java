package com.blog.socialshare.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PostSummery {
    Integer getId();
    String getTitle();
    String getExcerpt();

    User getAuthor();

    interface User {
        @Value("#{target.author.id}")
        String getId();
        @Value("#{target.author.name}")
        String getName();
    }
}
