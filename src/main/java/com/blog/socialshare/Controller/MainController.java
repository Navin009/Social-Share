package com.blog.socialshare.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(path = "")
    public String indexPage() {
        return "index";
    }

    @GetMapping(params = "search")
    public String searchPost(@RequestParam(value = "search") String search, Model model) {
        model.addAttribute("search", search);
        return "index";
    }

    @GetMapping(path = "newpost")
    public String newPost() {
        return "newpost";
    }

    @PostMapping(path = "newpost/save")
    public String savePost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String excerpt = request.getParameter("excerpt");
        String content = request.getParameter("content");
        return "redirect:/newpost";
    }

}