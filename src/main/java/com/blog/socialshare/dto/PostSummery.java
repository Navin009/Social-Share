package com.blog.socialshare.dto;

import java.sql.Timestamp;

public interface PostSummery {
    Integer getId();

    String getTitle();

    String getExcerpt();

    Timestamp getPublishedAt();

    User getAuthor();

    public interface User {
        Integer getId();

        String getName();
    }
}
