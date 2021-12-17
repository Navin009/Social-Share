package com.blog.socialshare.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping(path = "", params = "postid")
    public String pageblog(@RequestParam(value = "page") int val, Model model) {

        return "blog";
    }

    @GetMapping("/{post}")
    public String blog(@PathVariable("post") String post, Model model) {
        return "blog";
    }

}
