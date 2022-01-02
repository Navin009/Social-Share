package com.blog.socialshare.dto;

import java.sql.Timestamp;

public interface CommentDTO {
    Integer getId();

    String getName();

    String getEmail();

    String getComment();

    Timestamp getUpdatedAt();

    Timestamp getCreatedAt();

}
