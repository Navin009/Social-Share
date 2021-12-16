package com.blog.socialshare.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping(path = "", params = "page")
    public String pageblog(@RequestParam(value = "page") int val, Model model) {

        return "blog";
    }

    @RequestMapping(path = "", params = "page1")
    public String postblog(@RequestParam(value = "page1") int val, Model model) {

        return "blog";
    }
}
