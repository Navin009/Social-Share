package com.blog.socialshare.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {
    private Integer id;
    private String title;
    private String excerpt;
    private String content;
    private UserDTO author;
    private boolean isPublished;

    List<TagDTO> tags;
    List<CommentDTO> comments;

    public PostDTO(Integer id, String title, String excerpt, String content, Integer authorId, String authorName,
            String authorEmail, boolean isPublished) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.content = content;
        this.author = new UserDTO(authorId, authorName, authorEmail);
        this.isPublished = isPublished;
    }

}
