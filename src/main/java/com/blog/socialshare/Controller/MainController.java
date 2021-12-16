package com.blog.socialshare.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(path = "")
    public String index() {
        return "index";
    }

    @GetMapping(params = "search")
    public String searchedindex(@RequestParam(value = "search") String search, Model model) {
        model.addAttribute("search", search);
        return "index";
    }
}