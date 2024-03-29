package com.blog.socialshare.controller;

import java.util.List;

import com.blog.socialshare.model.Tag;
import com.blog.socialshare.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "tag/search")
    @ResponseBody
    public List<Tag> searchTag(@RequestParam("tag") String tag) {
        return tagService.getTagsByName(tag);
    }

}
