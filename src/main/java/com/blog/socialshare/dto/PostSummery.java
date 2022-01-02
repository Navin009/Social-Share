package com.blog.socialshare.dto;

import java.util.List;

public class PostSummery {
    Integer id;
    String title;
    String excerpt;
    String content;
    String author;
    List<TagDTO> tags;

    public PostSummery(Integer id, String title, String excerpt, String content, String author) {
        this.id = id;
        this.title = title;
        this.excerpt = excerpt;
        this.content = content;
        this.author = author;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

}
