package com.blog.socialshare.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @RequestMapping("/")
    public String blog() {
        return "blog";
    }
}
