package com.blog.socialshare.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    Integer id;
    String name;
    String email;
    String comment;
    Date updatedAt;
    Date createdAt;

}
