package com.blog.socialshare.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(path = {""}, params = "pagestart")
    public String index() {
        return "index";
    }

    @RequestMapping(path = { "" }, params = { "pagestart" , "pageend"})
    public String index1() {
        System.out.println("index1");
        return "index";
    }
}