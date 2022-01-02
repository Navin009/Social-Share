package com.blog.socialshare.dto;

import java.util.List;

public interface PostDTO {
    Integer getId();

    String getTitle();

    String getExcerpt();

    String getContent();
    
    UserDTO getAuthor();

    List<CommentDTO> getComments();

    List<TagDTO> getTags();
}
