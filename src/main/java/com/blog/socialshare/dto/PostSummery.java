package com.blog.socialshare.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSummery {
    Integer id;
    String title;
    String excerpt;
    Date publishedAt;
    UserDTO author;

    List<TagDTO> tags;

    public PostSummery(Integer id, String title, String excerpt, Date publishedAt, Integer authorId, String authorName,
            String authorEmail) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.publishedAt = publishedAt;
        this.author = new UserDTO(authorId, authorName, authorEmail);
    }

}
