package com.blog.socialshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    Integer id;
    String name;
    String email;
}
